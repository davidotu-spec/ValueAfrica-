package com.example.data.repository

import com.example.data.local.BookmarkedVendor
import com.example.data.local.VendorApplication
import com.example.data.local.VendorDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrlPlaceholder: String,
    val description: String,
    val isAvailableForExport: Boolean = true
)

data class Vendor(
    val id: String,
    val name: String,
    val category: String,
    val location: String,
    val description: String,
    val rating: Double,
    val logoPlaceholder: String,
    val products: List<Product>,
    val cacRegistered: Boolean = true
)

class ValueAfricaRepository(private val vendorDao: VendorDao) {

    // Preloaded static catalog of authentic African vendors and products
    val staticVendors = listOf(
        Vendor(
            id = "ngozi_textiles",
            name = "Ngozi Textiles & Weaving",
            category = "Fashion & Textiles",
            location = "Abuja, Nigeria",
            description = "Specialists in hand-woven premium Ankara prints, luxury Aso-Oke wraps, and custom contemporary wear celebrating Yoruba fashion ancestry.",
            rating = 4.9,
            logoPlaceholder = "N",
            products = listOf(
                Product("t1", "Ceremonial Ankara Wrap", 55.00, "wrap", "Exquisite indigo-dyed wrap tailored for special events with authentic embroidery details.", true),
                Product("t2", "Handcrafted Aso-Oke Cap", 24.00, "cap", "Traditional Nigerian cap hand-woven from durable organic cotton threads.", true),
                Product("t3", "Diva Silk Headtie", 30.00, "headtie", "Soft luxury silk-cotton blend headtie featuring asymmetric tribal line motifs.", true)
            ),
            cacRegistered = true
        ),
        Vendor(
            id = "kente_heritage",
            name = "Kente Heritage Craft Hub",
            category = "Crafts & Art",
            location = "Kumasi, Ghana",
            description = "A collective of multi-generational weavers producing ceremonial royal Kente cloths. Keeping Ashanti traditions vibrant for global appreciation.",
            rating = 5.0,
            logoPlaceholder = "K",
            products = listOf(
                Product("k1", "Royal Ashanti Kente Stole", 75.00, "stole", "Traditionally woven stole displaying symbolic red, yellow, and green geometric heritage strips.", true),
                Product("k2", "Premium Kente Throw Pillow Case", 29.00, "pillow", "Cozy standard-size decorative cushion sleeve woven with authentic loom-knotted patterns.", true)
            ),
            cacRegistered = true
        ),
        Vendor(
            id = "karibu_shea",
            name = "Karibu Shea Beauty Collective",
            category = "Beauty & Wellness",
            location = "Tamale, Ghana",
            description = "Empowering northern Ghanaian women by formulating raw, organic, premium-grade whipped Shea butter, cold-pressed oils, and botanicals.",
            rating = 4.8,
            logoPlaceholder = "S",
            products = listOf(
                Product("s1", "Raw Whipped Lavender Shea Butter", 14.50, "shea", "A rich, organic body lotion whipped with cold-pressed lavender oils for immediate absorption.", true),
                Product("s2", "Baobab & Moringa Repair Serum", 22.00, "serum", "Deeply nourishing botanical facial serum targeting elasticity and skin repair.", true)
            ),
            cacRegistered = true
        ),
        Vendor(
            id = "oyo_bronze",
            name = "Oyo Bronze Artisans",
            category = "Crafts & Art",
            location = "Oyo, Nigeria",
            description = "Master blacksmiths casting fine Yoruba bronze work, heritage statues, Yoruba coronation figurines, and custom brass sculpture commissions.",
            rating = 4.7,
            logoPlaceholder = "B",
            products = listOf(
                Product("b1", "Yoruba Coronation Bronze Figurine", 120.00, "figurine", "Solid brass, handcrafted coronation display designed by master smiths in Oyo.", true),
                Product("b2", "Tribal Spearhead Desk Weight", 38.00, "weight", "A stylized antique desk divider cast in premium heavy bronze.", true)
            ),
            cacRegistered = true
        ),
        Vendor(
            id = "serengeti_leather",
            name = "Serengeti Safari Leather",
            category = "Fashion & Textiles",
            location = "Arusha, Tanzania",
            description = "Crafting durable travel gear, full-grain rugged boots, and accessories using vegetable-tanned, sustainably sourced East African leather.",
            rating = 4.9,
            logoPlaceholder = "L",
            products = listOf(
                Product("l1", "Saddleback Explorer Duffle", 185.00, "duffle", "Heavy-duty waxed canvas and full-grain leather road bag with vintage brass buckles.", true),
                Product("l2", "Braided Leather Double Belt", 32.00, "belt", "Double-stitched classic leather belt hand-braided by Arusha leather workers.", true)
            ),
            cacRegistered = true
        ),
        Vendor(
            id = "atlas_argan",
            name = "Atlas Argan Organics",
            category = "Beauty & Wellness",
            location = "Marrakech, Morocco",
            description = "Authentic cosmetic and culinary Argan oils natively cold-pressed in Morocco by indigenous Amazigh women's cooperatives.",
            rating = 4.9,
            logoPlaceholder = "A",
            products = listOf(
                Product("a1", "Amazigh Cold-Pressed Argan Elixir", 28.00, "arganoil", "100% organic single-source cosmetic argan oil rich in Vitamin E.", true),
                Product("a2", "Rosewater Facial Cleanser Spray", 19.50, "rosewater", "Curated botanical spray made from damask roses harvested in Kelaat M'gouna.", true)
            ),
            cacRegistered = true
        ),
        Vendor(
            id = "karoo_biltong",
            name = "Karoo Biltong & Spice Co.",
            category = "Food & Agriculture",
            location = "Graaff-Reinet, South Africa",
            description = "Premium dried beef, wild game snack strips, and indigenous spice rubs sourced directly from sustainable Karoo farms.",
            rating = 4.6,
            logoPlaceholder = "F",
            products = listOf(
                Product("f1", "Traditional Karoo Beef Biltong (500g)", 20.00, "biltong", "Air-dried coriander and pepper spiced lean beef strips sliced thin.", true),
                Product("f2", "Chili Coriander Braai Rub", 10.00, "rub", "South African outdoor barbecue rub crafted with roasted chilis and sea salt.", true)
            ),
            cacRegistered = true
        )
    )

    // Room Database Interactions
    val bookmarkedVendorsState: Flow<List<BookmarkedVendor>> = vendorDao.getBookmarkedVendors()
    val vendorApplications: Flow<List<VendorApplication>> = vendorDao.getVendorApplications()

    suspend fun insertBookmark(vendor: Vendor) {
        vendorDao.insertBookmark(
            BookmarkedVendor(
                id = vendor.id,
                name = vendor.name,
                category = vendor.category,
                location = vendor.location,
                description = vendor.description,
                rating = vendor.rating,
                isBookmarked = true
            )
        )
    }

    suspend fun removeBookmark(id: String) {
        vendorDao.removeBookmarkById(id)
    }

    fun isBookmarked(id: String): Flow<Boolean> {
        return vendorDao.isVendorBookmarked(id)
    }

    suspend fun submitVendorApplication(
        businessName: String,
        category: String,
        address: String,
        email: String,
        description: String,
        cac: String
    ) {
        vendorDao.insertVendorApplication(
            VendorApplication(
                businessName = businessName,
                category = category,
                businessAddress = address,
                contactEmail = email,
                productDescription = description,
                cacNumber = cac,
                status = "Submitted"
            )
        )
    }

    suspend fun deleteApplication(id: Int) {
        vendorDao.deleteApplicationById(id)
    }
}
