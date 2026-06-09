package com.example.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.BookmarkedVendor
import com.example.data.local.VendorApplication
import com.example.data.repository.ValueAfricaRepository
import com.example.data.repository.Vendor
import com.example.data.network.GeminiClient
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class DashboardTab {
    Overview,
    Showcase,
    Dashboard,
    Strategy,
    AiStudio
}

class ValueAfricaViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val repository = ValueAfricaRepository(db.vendorDao())

    // Tabs
    var currentTab by mutableStateOf(DashboardTab.Overview)

    // Category Filter in Showcase
    var selectedCategory by mutableStateOf("All")

    // Bookmarks and Applications streams from database
    val bookmarkedVendors: StateFlow<List<BookmarkedVendor>> = repository.bookmarkedVendorsState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val vendorApplications: StateFlow<List<VendorApplication>> = repository.vendorApplications
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // All vendors data (reactive map of bookmarks)
    val staticVendorsList = repository.staticVendors

    // Selected Vendor for detailed view (null means list view)
    var selectedVendor by mutableStateOf<Vendor?>(null)

    // Vendor Application Form States
    var formBusinessName by mutableStateOf("")
    var formCategory by mutableStateOf("Fashion & Textiles")
    var formAddress by mutableStateOf("")
    var formEmail by mutableStateOf("")
    var formProductDesc by mutableStateOf("")
    var formCacNumber by mutableStateOf("")
    var formMessage by mutableStateOf("")
    var isSubmittingApplication by mutableStateOf(false)

    // Investor Strategy Simulator Settings
    var simVendorCount by mutableStateOf(1500f) // Slider: 100 - 10000
    var simAvgMonthlySales by mutableStateOf(350f) // Slider: 50 - 5000 (USD)
    var simCommissionRate by mutableStateOf(10f) // % commission (5 - 15)
    var simSubscriptionTier by mutableStateOf("Silver ($25/mo)") // Slider/Selector

    // Simulated Metrics
    val subscriptionPrice = derivedStateOf {
        when (simSubscriptionTier) {
            "None" -> 0.0
            "Bronze ($10/mo)" -> 10.0
            "Silver ($25/mo)" -> 25.0
            "Gold ($75/mo)" -> 75.0
            else -> 25.0
        }
    }

    val simulatedCommissionMonthly = derivedStateOf {
        (simVendorCount * simAvgMonthlySales * (simCommissionRate / 100.0))
    }

    val simulatedSubscriptionMonthly = derivedStateOf {
        (simVendorCount * subscriptionPrice.value)
    }

    val simulatedTotalMonthly = derivedStateOf {
        simulatedCommissionMonthly.value + simulatedSubscriptionMonthly.value
    }

    val simulatedTotalAnnual = derivedStateOf {
        simulatedTotalMonthly.value * 12.0
    }

    val simulatedArtisanIncomeCreated = derivedStateOf {
        simVendorCount * simAvgMonthlySales * (1.1 - (simCommissionRate / 100.0)) // Local multiplier
    }

    // Gemini AI Studio helper states
    var aiProductName by mutableStateOf("")
    var aiProductDetails by mutableStateOf("")
    var aiSelectedPromptType by mutableStateOf("Producer Pitch") // Or "Investor Brief"
    var aiPitchPromptOutput by mutableStateOf("")
    var isAiLoading by mutableStateOf(false)

    // UI actions
    fun toggleBookmark(vendor: Vendor) {
        viewModelScope.launch {
            val bookmarkedList = bookmarkedVendors.value
            val isBookmarked = bookmarkedList.any { it.id == vendor.id }
            if (isBookmarked) {
                repository.removeBookmark(vendor.id)
            } else {
                repository.insertBookmark(vendor)
            }
        }
    }

    fun submitNewApplication() {
        if (formBusinessName.isBlank() || formAddress.isBlank() || formEmail.isBlank()) {
            formMessage = "Please fill in all primary business fields."
            return
        }
        isSubmittingApplication = true
        viewModelScope.launch {
            try {
                repository.submitVendorApplication(
                    businessName = formBusinessName,
                    category = formCategory,
                    address = formAddress,
                    email = formEmail,
                    description = formProductDesc,
                    cac = formCacNumber
                )
                formMessage = "Hooray! Application for $formBusinessName drafted successfully!"
                // Reset form fields
                formBusinessName = ""
                formAddress = ""
                formEmail = ""
                formProductDesc = ""
                formCacNumber = ""
            } catch (e: Exception) {
                formMessage = "Submission failed: ${e.message}"
            } finally {
                isSubmittingApplication = false
            }
        }
    }

    fun deleteApplication(id: Int) {
        viewModelScope.launch {
            repository.deleteApplication(id)
        }
    }

    fun generateProductPitch() {
        if (aiProductName.isBlank()) {
            aiPitchPromptOutput = "Please provide at least a product name to generate."
            return
        }
        isAiLoading = true
        aiPitchPromptOutput = "Thinking... Asking ValueAfrica AI..."
        viewModelScope.launch {
            val systemPrompt = """
                You are the master Copywriter and Business Advisor for ValueAfrica Innovation Hub in Abuja.
                Write a polished, highly persuasive marketing pitch or strategic briefing based on the details below.
                Accentuate the rich cultural heritage (e.g. Nigerian, Ghanaian, Maasai, Moroccan Amazon), the 100% fair-trade aspects, and position it perfectly for a high-paying premium global or diaspora audience who wants authentic quality.
                
                Product-Name: $aiProductName
                Raw-Specs or Heritage Context: $aiProductDetails
                
                Always structures response elegantly using Markdown and highlights the premium value proposition. Specify recommended export steps if any.
            """.trimIndent()

            val response = GeminiClient.getGeminiResponse(systemPrompt)
            aiPitchPromptOutput = response
            isAiLoading = false
        }
    }

    fun runInvestorAIPreset(type: String) {
        isAiLoading = true
        aiPitchPromptOutput = "Calculating strategic landscape..."
        var query = ""
        when (type) {
            "logistic" -> {
                query = "Draft a comprehensive pitch describing ValueAfrica's custom shipping routes, parcel sorting in Abuja and Kumasi hub, DHL deep bulk deals, and the mitigation of African fragmentation."
            }
            "cac" -> {
                query = "Provide a deep-dive explanation of ValueAfrica's rigorous vendor verification pipeline. Specifically touch on CAC compliance in Nigeria, local trade guild references, and its impact on consumer trust."
            }
            "market" -> {
                query = "Evaluate the market cap size of the global African Diaspora in Europe and US desiring authentic African artifacts, and explain how ValueAfrica sub-channels solve their trust and transaction friction."
            }
        }
        viewModelScope.launch {
            val systemPrompt = """
                You are ValueAfrica's Chief Strategy Officer (CSO). Answer this strategic inquiry from a premium tier investor looking for structural security and logistics scalability.
                Inquiry: $query
                Use clear bold metrics. Keep it incredibly professional, realistic, and highly organized using proper paragraphs.
            """.trimIndent()
            val response = GeminiClient.getGeminiResponse(systemPrompt)
            aiPitchPromptOutput = response
            isAiLoading = false
        }
    }

    // Advanced Analytic Dashboard States & Actions
    var analyticsPeriod by mutableStateOf("Weekly") // "Weekly" or "Monthly"
    var analyticsCustomReportOutput by mutableStateOf("")
    var isAnalyticsReportLoading by mutableStateOf(false)

    fun generateAdvancedAnalyticsReport() {
        isAnalyticsReportLoading = true
        analyticsCustomReportOutput = "Generating $analyticsPeriod Executive Performance Report..."
        viewModelScope.launch {
            val systemPrompt = """
                You are the Chief Business Analyst at ValueAfrica Innovation Hub in Abuja.
                Generate a highly professional, detailed, and quantitative $analyticsPeriod summary report for the ValueAfrica network of creators and artisans.
                The report should include realistic and strategic insights about:
                1. Sales Trends over time ($analyticsPeriod sales metrics comparing active periods, average basket size, and order fulfillment rates).
                2. Customer Location Hotspots (focusing on the African Diaspora in US, UK, Canada, and EU buying patterns).
                3. Best Performing Product Categories (e.g., Handwoven Kente Textiles, Benin Bronze castings, Shea Butter wellness, and dynamic growth).
                4. Store Traffic Sources (Direct web-app storefront, Instagram shopping referrals, Diaspora trade associations, Etsy cross-listing API).
                
                Keep the tone elegant, professional, highly encouraging, and strictly organized with clear headers and percentages using markdown. Suggest 2 operational recommendations for artisans to increase conversions next period.
            """.trimIndent()
            try {
                val response = GeminiClient.getGeminiResponse(systemPrompt)
                analyticsCustomReportOutput = response
            } catch (e: Exception) {
                analyticsCustomReportOutput = "Failed to generate AI analysis: ${e.message}"
            } finally {
                isAnalyticsReportLoading = false
            }
        }
    }
}
