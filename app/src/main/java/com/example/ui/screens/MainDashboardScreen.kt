package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.BookmarkedVendor
import com.example.data.local.VendorApplication
import com.example.data.repository.Vendor
import com.example.ui.theme.LeafTeal
import com.example.ui.theme.SunOchre
import com.example.ui.theme.Terracotta
import com.example.ui.theme.DeepForestSurface
import com.example.ui.theme.TextLight
import com.example.ui.viewmodel.DashboardTab
import com.example.ui.viewmodel.ValueAfricaViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDashboardScreen(
    viewModel: ValueAfricaViewModel,
    modifier: Modifier = Modifier
) {
    val bookmarkedList by viewModel.bookmarkedVendors.collectAsStateWithLifecycle()
    val applicationsList by viewModel.vendorApplications.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Terracotta),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "V",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                "ValueAfrica",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Text(
                                "INNOVATION HUB",
                                letterSpacing = 2.sp,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = LeafTeal
                            )
                        }
                    }
                },
                actions = {
                    if (viewModel.selectedVendor != null) {
                        IconButton(onClick = { viewModel.selectedVendor = null }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Detail"
                            )
                        }
                    } else {
                        // Custom Natural Tones Profile Avatar
                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE6CCB2))
                                .border(1.5.dp, Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .border(2.dp, Terracotta, CircleShape)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                NavigationBarItem(
                    selected = viewModel.currentTab == DashboardTab.Overview,
                    onClick = {
                        viewModel.currentTab = DashboardTab.Overview
                        viewModel.selectedVendor = null
                    },
                    icon = { Icon(Icons.Default.Info, contentDescription = "About") },
                    label = { Text("Profile") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Terracotta,
                        selectedTextColor = Terracotta,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                NavigationBarItem(
                    selected = viewModel.currentTab == DashboardTab.Showcase,
                    onClick = { viewModel.currentTab = DashboardTab.Showcase },
                    icon = { Icon(Icons.Default.List, contentDescription = "Market") },
                    label = { Text("Marketplace") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Terracotta,
                        selectedTextColor = Terracotta,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                NavigationBarItem(
                    selected = viewModel.currentTab == DashboardTab.Dashboard,
                    onClick = {
                        viewModel.currentTab = DashboardTab.Dashboard
                        viewModel.selectedVendor = null
                    },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Dashboard") },
                    label = { Text("Dashboard") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Terracotta,
                        selectedTextColor = Terracotta,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                NavigationBarItem(
                    selected = viewModel.currentTab == DashboardTab.Strategy,
                    onClick = {
                        viewModel.currentTab = DashboardTab.Strategy
                        viewModel.selectedVendor = null
                    },
                    icon = { Icon(Icons.Default.Build, contentDescription = "Strategy") },
                    label = { Text("Simulator") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Terracotta,
                        selectedTextColor = Terracotta,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
                NavigationBarItem(
                    selected = viewModel.currentTab == DashboardTab.AiStudio,
                    onClick = {
                        viewModel.currentTab = DashboardTab.AiStudio
                        viewModel.selectedVendor = null
                    },
                    icon = { Icon(Icons.Default.Star, contentDescription = "AI Studio") },
                    label = { Text("AI Studio") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Terracotta,
                        selectedTextColor = Terracotta,
                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = viewModel.currentTab,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "TabTransition"
            ) { tab ->
                when (tab) {
                    DashboardTab.Overview -> OverviewTab(viewModel)
                    DashboardTab.Showcase -> ShowcaseTab(viewModel, bookmarkedList, applicationsList)
                    DashboardTab.Dashboard -> VendorDashboardTab(viewModel)
                    DashboardTab.Strategy -> StrategyTab(viewModel)
                    DashboardTab.AiStudio -> AiStudioTab(viewModel)
                }
            }
        }
    }
}

// ==========================================
// COLUMN 1: CORPORATE PROFILE & PITCH DECK
// ==========================================
@Composable
fun OverviewTab(viewModel: ValueAfricaViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)
    ) {
        // Hero / Header Banner with unique background
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier
                                .background(LeafTeal, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                "CORPORATE PROFILE",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        
                        Text(
                            "ValueAfrica Innovation Hub",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black,
                            color = Terracotta
                        )
                        
                        Text(
                            "A premium digital gateway and e-commerce infrastructure powering Africa's indigenous producers to connect with secure global export pathways.",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.CheckCircle, "CAC Verified", tint = LeafTeal, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("CAC RC-18459 Nigeria", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                            }
                            Text("Abuja Headquarters", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = LeafTeal)
                        }
                    }
                }
            }
        }

        // Mission & Vision card with elegant dual columns
        item {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    "Our Narrative Mandate",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Terracotta
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = Terracotta),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Star, "Vision", tint = Color.White, modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Vision", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "To become Africa’s most trusted digital marketplace which champions fair value and global reach for indigenous craftsmanship.",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 16.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.cardColors(containerColor = LeafTeal),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Info, "Mission", tint = Color.White, modifier = Modifier.size(18.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Mission", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = Color.White)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "To empower African creators and SMEs via digital visibility, comprehensive brand packaging, and frictionless logistics infrastructure.",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }
        }

        // Core Values row list
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    "Our Core Values",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Terracotta
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val values = listOf("Innovation", "Integrity", "Quality", "Cultural Pride", "Fair Trade", "Inclusivity")
                    items(values) { value ->
                        Box(
                            modifier = Modifier
                                .border(1.dp, LeafTeal.copy(alpha = 0.4f), RoundedCornerShape(50.dp))
                                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(50.dp))
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.size(6.dp).background(SunOchre, CircleShape))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(value, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Market Opportunity & Gap
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Market Opportunity & Cleared Gaps",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Terracotta
                    )

                    Text(
                        "African creators create rich masterpieces but suffer from crippling constraints. ValueAfrica directly solves this structural bottleneck:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    val gaps = listOf(
                        "Lack of high-conversion digital shopfronts" to "ValueAfrica unified e-commerce hub",
                        "Fragmented escrow and currency bottlenecks" to "Secure multi-payment and escrow vaults",
                        "High international shipping compliance barriers" to "Pre-vetted bulk export cargo partnerships",
                        "Low brand positioning or product imagery" to "Centralized product verification & photography"
                    )

                    gaps.forEach { gap ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Icon(Icons.Default.Close, "Gap", tint = Color.Red.copy(alpha = 0.7f), modifier = Modifier.size(16.dp).padding(top = 2.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(gap.first, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                Spacer(modifier = Modifier.width(4.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Check, "Solution", tint = LeafTeal, modifier = Modifier.size(12.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(gap.second, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LeafTeal)
                                }
                            }
                        }
                    }
                }
            }
        }

        // strategic positioning card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = DeepForestSurface)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(SunOchre.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Settings, "Hub Strategy", tint = SunOchre, modifier = Modifier.size(24.dp))
                    }
                    Column {
                        Text(
                            "Strategic Market Nexus",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = SunOchre
                        )
                        Text(
                            "Positions at the intersection of Digital E-Commerce, MSME Empowerment, Cultural Branding, and Export Trade facilitation. Creating Africa's trust gateway.",
                            fontSize = 11.sp,
                            color = TextLight.copy(alpha = 0.8f),
                            lineHeight = 15.sp
                        )
                    }
                }
            }
        }

        // Business objectives with progress bar elements
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        "Business Metrics (3-Year Plan)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Terracotta
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Onboard 10,000 Verified Sellers", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("15%", fontSize = 11.sp, color = LeafTeal, fontWeight = FontWeight.Bold)
                        }
                        LinearProgressIndicator(progress = 0.15f, color = LeafTeal, trackColor = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.fillMaxWidth())
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Export Pathways (Air & Sea freight deals)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("60%", fontSize = 11.sp, color = LeafTeal, fontWeight = FontWeight.Bold)
                        }
                        LinearProgressIndicator(progress = 0.60f, color = LeafTeal, trackColor = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.fillMaxWidth())
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                            Text("Abuja & Kumasi Sorting Centers Operations", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("90%", fontSize = 11.sp, color = LeafTeal, fontWeight = FontWeight.Bold)
                        }
                        LinearProgressIndicator(progress = 0.90f, color = LeafTeal, trackColor = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }

        // Operations executive team list
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    "Our Operations Team Structure",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Terracotta
                )

                val members = listOf(
                    Triple("Founder & Chief Executive Officer", "Strategy, investment and pan-African policy direction.", "Abuja"),
                    Triple("Chief Technology Officer", "Maintains marketplace, vendor portals, and database metrics.", "Remote"),
                    Triple("Logistics Lead & Customer Support", "Ensures compliance check and seamless global export routes.", "Lagos"),
                    Triple("Vendor Acquisition & Partnership Lead", "Onboarding local weavers, bronze smiths, and agro-producers.", "Kumasi")
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    members.forEach { member ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(Terracotta.copy(alpha = 0.1f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.AccountBox, "Operations Team", tint = Terracotta)
                                }
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(member.first, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(member.second, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f))
                                }
                                Text(
                                    text = member.third,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LeafTeal,
                                    modifier = Modifier
                                        .background(LeafTeal.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// COLUMN 2: VENDOR DIRECTORY & SHOWCASE (ROOM)
// ==========================================
@Composable
fun ShowcaseTab(
    viewModel: ValueAfricaViewModel,
    bookmarkedList: List<BookmarkedVendor>,
    applicationsList: List<VendorApplication>
) {
    var showApplicationSheet by remember { mutableStateOf(false) }

    if (viewModel.selectedVendor != null) {
        VendorDetailView(viewModel, viewModel.selectedVendor!!, bookmarkedList)
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp)
        ) {
            // Introductory message
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "African Maker Hubs",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Terracotta
                        )
                        Text(
                            "Preloaded verified local enterprise catalogs.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                        )
                    }

                    Button(
                        onClick = { showApplicationSheet = !showApplicationSheet },
                        colors = ButtonDefaults.buttonColors(containerColor = LeafTeal),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("apply_hub_button")
                    ) {
                        Icon(Icons.Default.Add, "Join", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Apply Hub", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Expandable dynamic form card to enlist the User's Vendor Application (persisted in Room Db)
            if (showApplicationSheet) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                "Draft Verified Partnership Application",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = Terracotta
                            )

                            Text(
                                "Registered CAC businesses in Abuja, Kumasi, or Lagos get faster approval. Saved draft is stored in your Room Database SQLite memory.",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            OutlinedTextField(
                                value = viewModel.formBusinessName,
                                onValueChange = { viewModel.formBusinessName = it },
                                label = { Text("Business / Store Name") },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("form_name_input"),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Terracotta,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                )
                            )

                            OutlinedTextField(
                                value = viewModel.formAddress,
                                onValueChange = { viewModel.formAddress = it },
                                label = { Text("Abuja / Physical Workspace Address") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Terracotta
                                )
                            )

                            OutlinedTextField(
                                value = viewModel.formCacNumber,
                                onValueChange = { viewModel.formCacNumber = it },
                                label = { Text("Nigeria CAC Number (e.g. RC-123456)") },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Terracotta
                                )
                            )

                            OutlinedTextField(
                                value = viewModel.formEmail,
                                onValueChange = { viewModel.formEmail = it },
                                label = { Text("Contact Email Address") },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Terracotta
                                )
                            )

                            OutlinedTextField(
                                value = viewModel.formProductDesc,
                                onValueChange = { viewModel.formProductDesc = it },
                                label = { Text("Indigenous Products Specialties") },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Terracotta
                                )
                            )

                            // Dropdown/Selector representation
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Category Category:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    listOf("Fashion & Textiles", "Crafts & Art", "Beauty & Wellness").forEach { cat ->
                                        val isSel = viewModel.formCategory == cat
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    if (isSel) Terracotta else MaterialTheme.colorScheme.surface,
                                                    RoundedCornerShape(4.dp)
                                                )
                                                .clickable { viewModel.formCategory = cat }
                                                .padding(horizontal = 6.dp, vertical = 4.dp)
                                        ) {
                                            Text(
                                                cat.take(7) + "..",
                                                fontSize = 11.sp,
                                                color = if (isSel) Color.White else MaterialTheme.colorScheme.onSurface,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }

                            if (viewModel.formMessage.isNotBlank()) {
                                Text(
                                    viewModel.formMessage,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LeafTeal,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = { showApplicationSheet = false }) {
                                    Text("Dismiss", color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = { viewModel.submitNewApplication() },
                                    colors = ButtonDefaults.buttonColors(containerColor = Terracotta),
                                    modifier = Modifier.testTag("form_submit_button")
                                ) {
                                    if (viewModel.isSubmittingApplication) {
                                        CircularProgressIndicator(modifier = Modifier.size(14.dp), color = Color.White)
                                    } else {
                                        Text("Save Draft", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Categories row switches
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val categories = listOf("All", "Fashion & Textiles", "Crafts & Art", "Beauty & Wellness", "Food & Agriculture")
                    items(categories) { category ->
                        val isSelected = viewModel.selectedCategory == category
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) Terracotta else MaterialTheme.colorScheme.surface)
                                .clickable { viewModel.selectedCategory = category }
                                .padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = category,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }

            // Dynamic count row
            item {
                val count = viewModel.staticVendorsList.filter {
                    viewModel.selectedCategory == "All" || it.category == viewModel.selectedCategory
                }.size
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Showing $count Hub Partnerships",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = LeafTeal
                    )

                    if (bookmarkedList.isNotEmpty()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Favorite, "Favs", tint = Color.Red, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("${bookmarkedList.size} Bookmarked", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // List of vendors
            val filteredVendors = viewModel.staticVendorsList.filter {
                viewModel.selectedCategory == "All" || it.category == viewModel.selectedCategory
            }

            items(filteredVendors) { vendor ->
                val isFav = bookmarkedList.any { it.id == vendor.id }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.selectedVendor = vendor }
                        .testTag("vendor_card_${vendor.id}"),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(14.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Custom logo bubble styled in Natural Tones
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE6CCB2))
                                .border(1.5.dp, Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                vendor.logoPlaceholder,
                                color = Terracotta,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    vendor.name,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                if (vendor.cacRegistered) {
                                    Box(
                                        modifier = Modifier
                                            .background(LeafTeal.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                    ) {
                                        Text("CAC", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = LeafTeal)
                                    }
                                }
                            }

                            Text(
                                "${vendor.category} • ${vendor.location}",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                vendor.description,
                                fontSize = 12.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.82f)
                            )
                        }

                        // Bookmark action bubble
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            IconButton(onClick = { viewModel.toggleBookmark(vendor) }) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Bookmark",
                                    tint = if (isFav) Color.Red else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                                )
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Icon(Icons.Default.Star, "Rating", tint = SunOchre, modifier = Modifier.size(11.dp))
                                Text(vendor.rating.toString(), fontSize = 11.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
            }

            // SQLite Saved Applications representation
            if (applicationsList.isNotEmpty()) {
                item {
                    Text(
                        "Your Core Submissions (${applicationsList.size} Drafts saved)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Terracotta,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }

                items(applicationsList) { app ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(Icons.Default.Check, "Doc", tint = LeafTeal)
                            Column(modifier = Modifier.weight(1f)) {
                                Text(app.businessName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Text("Specialty: ${app.productDescription}", fontSize = 12.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("CAC: ${app.cacNumber.ifBlank { "N/A" }}", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    Box(
                                        modifier = Modifier
                                            .background(LeafTeal, RoundedCornerShape(3.dp))
                                            .padding(horizontal = 4.dp, vertical = 1.dp)
                                    ) {
                                        Text(app.status, color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                            IconButton(onClick = { viewModel.deleteApplication(app.id) }) {
                                Icon(Icons.Default.Delete, "Delete", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// VENDOR DETAILED SCREEN WITH PRODUCT BROCHURE
// ==========================================
@Composable
fun VendorDetailView(
    viewModel: ValueAfricaViewModel,
    vendor: Vendor,
    bookmarkedList: List<BookmarkedVendor>
) {
    val isFav = bookmarkedList.any { it.id == vendor.id }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 48.dp)
    ) {
        // Back Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { viewModel.selectedVendor = null }
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Go Back", tint = Terracotta)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Markets Directory", color = Terracotta, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }

                IconButton(onClick = { viewModel.toggleBookmark(vendor) }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Bookmark",
                        tint = if (isFav) Color.Red else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }

        // Main info header card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            vendor.category,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = LeafTeal
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, "Star", tint = SunOchre, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(vendor.rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Text(
                        vendor.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Terracotta
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Home, "Loc", tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(vendor.location, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }

                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))

                    Text(
                        vendor.description,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                    )
                }
            }
        }

        // Product Catalog
        item {
            Text(
                "Export-Ready Indigenous Catalogue (${vendor.products.size} Items)",
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                color = Terracotta
            )
        }

        items(vendor.products) { product ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                product.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            "$${product.price} USD",
                            fontWeight = FontWeight.Black,
                            fontSize = 15.sp,
                            color = LeafTeal
                        )
                    }

                    Text(
                        product.description,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Check, "Secure Ready", tint = LeafTeal, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Standard Export Packaging", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = LeafTeal)
                        }

                        if (product.isAvailableForExport) {
                            Box(
                                modifier = Modifier
                                    .background(LeafTeal, RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("EXPORT SECURED", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// COLUMN 3: REVENUE SIMULATOR & GROWTH ESTIMATOR
// ==========================================
@Composable
fun StrategyTab(viewModel: ValueAfricaViewModel) {
    val usdFormat = remember { NumberFormat.getCurrencyInstance(Locale.US) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 48.dp)
    ) {
        // Operational milestones card
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        "Interactive Operational Horizon",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Terracotta
                    )

                    Text(
                        "Click each implementation level to review our operational metrics:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    val phases = listOf(
                        Triple("Phase 1: Setup Infrastructure", "Months 0-3", "Federal CAC registrations complete, digital escrow frameworks integrated, first 100 Abuja artisans registered."),
                        Triple("Phase 2: Abuja/Lagos Commercial Launch", "Months 3-6", "Active digital payment hub live. Onboard 500 SMEs with professional photography support."),
                        Triple("Phase 3: Deep Pan-African Integration", "Months 6-12", "Kumasi (Ghana) sorting warehouse launch, local micro-broker partnerships, and mobile apps (Phase 2) release."),
                        Triple("Phase 4: Global Bulk Export Engine", "Years 1-3", "Custom bulk export treaties with airlines, launching secure warehouses in US & UK Diaspora portals.")
                    )

                    var expandedPhase by remember { mutableStateOf(-1) }

                    phases.forEachIndexed { idx, phase ->
                        val isExp = expandedPhase == idx
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (isExp) Terracotta.copy(alpha = 0.05f) else Color.Transparent,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { expandedPhase = if (isExp) -1 else idx }
                                .padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(if (isExp) Terracotta else LeafTeal),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            (idx + 1).toString(),
                                            color = Color.White,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        phase.first,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = if (isExp) Terracotta else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                                Text(
                                    phase.second,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = LeafTeal
                                )
                            }
                            if (isExp) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    phase.third,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                                    lineHeight = 16.sp,
                                    modifier = Modifier.padding(start = 32.dp)
                                )
                            }
                        }
                        if (idx < phases.lastIndex) {
                            Divider(
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f),
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        // Live Growth Strategy Simulator
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("strategy_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        "Live Revenue & Impact Estimator",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Terracotta
                    )

                    Text(
                        "Slide variables representing active hub sizes and global transaction flows to simulate ValueAfrica commissions and local income generation:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    // SLA 1: Vendor count
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Active African SMEs Partners:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("${viewModel.simVendorCount.toInt()} Hubs", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Terracotta)
                        }
                        Slider(
                            value = viewModel.simVendorCount,
                            onValueChange = { viewModel.simVendorCount = it },
                            valueRange = 100f..10000f,
                            colors = SliderDefaults.colors(
                                thumbColor = Terracotta,
                                activeTrackColor = Terracotta,
                                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                    }

                    // SLA 2: Monthly Sales
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Average Monthly Hub Sales:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("$${viewModel.simAvgMonthlySales.toInt()} USD", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Terracotta)
                        }
                        Slider(
                            value = viewModel.simAvgMonthlySales,
                            onValueChange = { viewModel.simAvgMonthlySales = it },
                            valueRange = 50f..2000f,
                            colors = SliderDefaults.colors(
                                thumbColor = Terracotta,
                                activeTrackColor = Terracotta,
                                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                    }

                    // SLA 3: Commission rate
                    Column {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("ValueAfrica Commission Share:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            Text("${viewModel.simCommissionRate.toInt()}%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LeafTeal)
                        }
                        Slider(
                            value = viewModel.simCommissionRate,
                            onValueChange = { viewModel.simCommissionRate = it },
                            valueRange = 5f..15f,
                            colors = SliderDefaults.colors(
                                thumbColor = LeafTeal,
                                activeTrackColor = LeafTeal,
                                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                    }

                    // Subscription Tiers Choice
                    Column {
                        Text("SaaS Premium Subscription Package:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(bottom = 6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            val tiers = listOf("None", "Bronze ($10/mo)", "Silver ($25/mo)", "Gold ($75/mo)")
                            tiers.forEach { tier ->
                                val isSelected = viewModel.simSubscriptionTier == tier
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            if (isSelected) LeafTeal else MaterialTheme.colorScheme.surfaceVariant,
                                            RoundedCornerShape(6.dp)
                                        )
                                        .clickable { viewModel.simSubscriptionTier = tier }
                                        .padding(vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        tier.split(" ").first(),
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Calculation Dashboard Panel (High priority metrics visualization)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = DeepForestSurface),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                "Platform Projected Run-Rate & Impact",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = SunOchre,
                                letterSpacing = 1.sp
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Monthly Commissions", fontSize = 10.sp, color = TextLight.copy(alpha = 0.6f))
                                    Text(
                                        usdFormat.format(viewModel.simulatedCommissionMonthly.value),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Black,
                                        color = TextLight
                                    )
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Monthly Subscription Fee", fontSize = 10.sp, color = TextLight.copy(alpha = 0.6f))
                                    Text(
                                        usdFormat.format(viewModel.simulatedSubscriptionMonthly.value),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Black,
                                        color = TextLight
                                    )
                                }
                            }

                            Divider(color = TextLight.copy(alpha = 0.1f))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Column {
                                    Text("Total Platform Revenue/Year", fontSize = 11.sp, color = SunOchre)
                                    Text(
                                        usdFormat.format(viewModel.simulatedTotalAnnual.value),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Black,
                                        color = SunOchre
                                    )
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Text("Artisan Wealth Disbursed", fontSize = 11.sp, color = LeafTeal)
                                    Text(
                                        usdFormat.format(viewModel.simulatedArtisanIncomeCreated.value),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = LeafTeal
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// ==========================================
// COLUMN 4: GEMINI AI PITCH & BRIEF STUDIO
// ==========================================
@Composable
fun AiStudioTab(viewModel: ValueAfricaViewModel) {
    var aiSelection by remember { mutableStateOf("Vendor Pitch") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 48.dp)
    ) {
        // Welcome Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .background(SunOchre.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            "GEMINI AI PARTNERSHIP ENGINE",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Terracotta
                        )
                    }
                    Text(
                        "ValueAfrica AI Creator Studio",
                        fontWeight = FontWeight.Black,
                        fontSize = 20.sp,
                        color = Terracotta
                    )
                    Text(
                        "Formulate export-ready listings for producers or review structural mechanics for prospective international investors.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Segment switch tabs for Producer vs Investor
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { aiSelection = "Vendor Pitch" },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (aiSelection == "Vendor Pitch") Terracotta else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Maker Listing Pitch",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (aiSelection == "Vendor Pitch") Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Button(
                    onClick = { aiSelection = "Investor QA" },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (aiSelection == "Investor QA") Terracotta else MaterialTheme.colorScheme.surfaceVariant
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        "Investor Counsel QA",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (aiSelection == "Investor QA") Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Active workspace
        if (aiSelection == "Vendor Pitch") {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("ai_studio_card"),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("Draft High-Value Export Listing Pitch", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        
                        OutlinedTextField(
                            value = viewModel.aiProductName,
                            onValueChange = { viewModel.aiProductName = it },
                            label = { Text("Product / Artifact Name") },
                            placeholder = { Text("e.g. Royal Hand-Loomed Kente Cushion") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("ai_product_input"),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Terracotta)
                        )

                        OutlinedTextField(
                            value = viewModel.aiProductDetails,
                            onValueChange = { viewModel.aiProductDetails = it },
                            label = { Text("Raw Craft Materials & Heritage Context") },
                            placeholder = { Text("e.g. Premium wool, gold & black geometric line. Sourced from weavers cooperative in southern Nigeria") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Terracotta)
                        )

                        Button(
                            onClick = { viewModel.generateProductPitch() },
                            colors = ButtonDefaults.buttonColors(containerColor = Terracotta),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("generate_pitch_button")
                        ) {
                            if (viewModel.isAiLoading) {
                                CircularProgressIndicator(modifier = Modifier.size(18.dp), color = Color.White)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Weaving heritage description...")
                            } else {
                                Icon(Icons.Default.Star, "Gen", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Generate Authentic Product Story", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        } else {
            // Investor preset topics
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("Select Strategic Topic for AI Insights", fontWeight = FontWeight.Bold, fontSize = 14.sp)

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(LeafTeal.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .clickable { viewModel.runInvestorAIPreset("logistic") }
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(LeafTeal, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.PlayArrow, "Logistics", tint = Color.White, modifier = Modifier.size(12.dp))
                                }
                                Text("Describe Global Export & Sorting Hub Dynamics", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LeafTeal)
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Terracotta.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                    .clickable { viewModel.runInvestorAIPreset("cac") }
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(Terracotta, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.PlayArrow, "Trust", tint = Color.White, modifier = Modifier.size(12.dp))
                                }
                                Text("How Nigeria CAC Verification Eliminates Risk", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Terracotta)
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(SunOchre.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                    .clickable { viewModel.runInvestorAIPreset("market") }
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(SunOchre, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.PlayArrow, "Market", tint = Color.Black, modifier = Modifier.size(12.dp))
                                }
                                Text("Diaspora Buying Gaps Evaluated", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                            }
                        }
                    }
                }
            }
        }

        // Unified output card
        if (viewModel.aiPitchPromptOutput.isNotBlank()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Generated Insights & Authentic Textures",
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                color = LeafTeal,
                                letterSpacing = 0.5.sp
                            )
                            if (viewModel.isAiLoading) {
                                CircularProgressIndicator(modifier = Modifier.size(12.dp))
                            } else {
                                Icon(Icons.Default.CheckCircle, "Loaded", tint = LeafTeal, modifier = Modifier.size(16.dp))
                            }
                        }

                        Divider(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f))

                        Text(
                            text = viewModel.aiPitchPromptOutput,
                            fontSize = 13.sp,
                            lineHeight = 19.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun VendorDashboardTab(viewModel: ValueAfricaViewModel) {
    val usdFormat = remember { NumberFormat.getCurrencyInstance(Locale.US) }
    val period = viewModel.analyticsPeriod
    val isWeekly = period == "Weekly"

    // Data for the Analytics trends
    val weeklyData = listOf(150f, 320f, 240f, 480f, 510f, 390f, 620f)
    val weeklyLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    
    val monthlyData = listOf(2400f, 3800f, 3100f, 4900f, 5600f, 6800f)
    val monthlyLabels = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("vendor_dashboard_container"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 48.dp)
    ) {
        // Dashboard Title Header
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Advanced Analytic Center",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Terracotta
                        )
                        Box(
                            modifier = Modifier
                                .background(LeafTeal, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("LIVE FEED", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Text(
                        "Review sales trends, diaspora location hotspots, traffic metrics, and generate strategic summary reports.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Interval Period Switcher
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Set Analytics Reporting Interval",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Weekly", "Monthly").forEach { interval ->
                            val isSelected = period == interval
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        if (isSelected) Terracotta else MaterialTheme.colorScheme.surfaceVariant,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .clickable { viewModel.analyticsPeriod = interval }
                                    .padding(vertical = 10.dp)
                                    .testTag("interval_tab_$interval"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$interval Reports",
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section: Sales Trends Over Time Graphic
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("sales_trends_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, "Trends", tint = Terracotta, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Sales Trends Over Time", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                        }
                        Text(
                            text = if (isWeekly) "+24% vs last week" else "+38% vs last month",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = LeafTeal
                        )
                    }

                    // Native Canvas Chart Drawing
                    Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            val maxVal = if (isWeekly) 700f else 8000f
                            val data = if (isWeekly) weeklyData else monthlyData
                            
                            val width = size.width
                            val height = size.height
                            val numPoints = data.size
                            val xSpacing = width / (numPoints - 1)
                            
                            // Draw light horizontal grid lines
                            val gridLines = 4
                            for (i in 0..gridLines) {
                                val y = height * (i / gridLines.toFloat())
                                drawLine(
                                    color = Color.LightGray.copy(alpha = 0.15f),
                                    start = androidx.compose.ui.geometry.Offset(0f, y),
                                    end = androidx.compose.ui.geometry.Offset(width, y),
                                    strokeWidth = 1.dp.toPx()
                                )
                            }
                            
                            val path = Path()
                            val fillPath = Path()
                            
                            data.forEachIndexed { idx, value ->
                                val x = idx * xSpacing
                                val y = height - (value / maxVal) * (height * 0.75f) - (height * 0.12f)
                                
                                if (idx == 0) {
                                    path.moveTo(x, y)
                                    fillPath.moveTo(x, height)
                                    fillPath.lineTo(x, y)
                                } else {
                                    path.lineTo(x, y)
                                    fillPath.lineTo(x, y)
                                }
                                
                                if (idx == data.size - 1) {
                                    fillPath.lineTo(x, height)
                                    fillPath.close()
                                }
                            }
                            
                            // Draw backdrop gradient
                            drawPath(
                                path = fillPath,
                                brush = Brush.linearGradient(
                                    colors = listOf(Terracotta.copy(alpha = 0.2f), Color.Transparent),
                                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                                    end = androidx.compose.ui.geometry.Offset(0f, height)
                                )
                            )
                            
                            // Draw outline path
                            drawPath(
                                path = path,
                                color = Terracotta,
                                style = Stroke(width = 3.dp.toPx())
                            )
                            
                            // Draw nodes
                            data.forEachIndexed { idx, value ->
                                val x = idx * xSpacing
                                val y = height - (value / maxVal) * (height * 0.75f) - (height * 0.12f)
                                drawCircle(
                                    color = Terracotta,
                                    radius = 4.5.dp.toPx(),
                                    center = androidx.compose.ui.geometry.Offset(x, y)
                                )
                                drawCircle(
                                    color = Color.White,
                                    radius = 2.dp.toPx(),
                                    center = androidx.compose.ui.geometry.Offset(x, y)
                                )
                            }
                        }
                    }

                    // X-axis text labels
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val labels = if (isWeekly) weeklyLabels else monthlyLabels
                        labels.forEach { label ->
                            Text(
                                text = label,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    }

                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))

                    // Spark Metrics Grid
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Partner Revenue", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                            Text(
                                usdFormat.format(if (isWeekly) 2710 else 26600),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Terracotta
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Fulfilled Orders", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                            Text(
                                if (isWeekly) "18 Batches" else "142 Shipments",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = LeafTeal
                            )
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Basket Average", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                            Text(
                                "$150.55",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = SunOchre
                            )
                        }
                    }
                }
            }
        }

        // Section: Customer Location Hotspots Progress Bars
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("location_distribution_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Home, "Location", tint = LeafTeal, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Customer Location Hotspots (Diaspora)", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                    }

                    Text(
                        "Where Diaspora and international buyers of authentic craftsmanship originate:",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    val locations = listOf(
                        Triple("United States (East Coast - NY/MD/DC)", 0.42f, "42%"),
                        Triple("United Kingdom (London sorter hub)", 0.24f, "24%"),
                        Triple("Canada (Toronto GTA)", 0.14f, "14%"),
                        Triple("Federal Republic (Abuja / Lagos Local)", 0.10f, "10%"),
                        Triple("European Union (Frankfurt / Paris)", 0.06f, "6%"),
                        Triple("Ghana (Accra Trade House)", 0.04f, "4%")
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        locations.forEach { (loc, progress, pct) ->
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(loc, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                                    Text(pct, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = LeafTeal)
                                }
                                LinearProgressIndicator(
                                    progress = progress,
                                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                                    color = if (progress > 0.2f) Terracotta else LeafTeal,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        // Section: Product Performance Comparisons list
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("product_performance_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ShoppingCart, "Products", tint = SunOchre, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Product Performance & Provenance", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                    }

                    val productsList = listOf(
                        Triple("Handwoven Premium Kente", "$4,580 (Fulfillment: 100%)", "+45% Growth"),
                        Triple("Emperor Benin Bronze Castings", "$3,220 (Fulfillment: 96%)", "+12% Growth"),
                        Triple("Organic Shea Butter - Gold Pack", "$2,840 (Fulfillment: 98%)", "+30% Growth"),
                        Triple("Aso Oke Minimalist Modern Trench", "$1,810 (Fulfillment: 100%)", "+75% Growth")
                    )

                    productsList.forEach { (prod, metric, growth) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(prod, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                Spacer(modifier = Modifier.height(2.dp))
                                Text("Sales: $metric", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                            }
                            Box(
                                modifier = Modifier
                                    .background(LeafTeal.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Text(growth, color = LeafTeal, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold)
                            }
                        }
                    }
                }
            }
        }

        // Section: Traffic Sources
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("traffic_sources_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Share, "Traffic", tint = Terracotta, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Storefront Traffic Sources", fontWeight = FontWeight.Bold, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurface)
                    }

                    val traffic = listOf(
                        Pair("Instagram Ads & Creator Referrals", "38% Traffic Share"),
                        Pair("Direct Organic Storefront Link", "28% Traffic Share"),
                        Pair("Diaspora Trade Forums & Portals", "18% Traffic Share"),
                        Pair("Etsy Partner Integration API", "11% Traffic Share"),
                        Pair("Google Organic Search Engine", "5% Traffic Share")
                    )

                    traffic.forEach { (src, share) ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(CircleShape)
                                        .background(Terracotta)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(src, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            }
                            Text(share, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Terracotta)
                        }
                    }
                }
            }
        }

        // Section: Summary Report Generation Card via Gemini AI
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("ai_report_generator_card"),
                colors = CardDefaults.cardColors(containerColor = DeepForestSurface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Gemini Executive Analytical Report",
                            fontWeight = FontWeight.ExtraBold,
                            color = SunOchre,
                            fontSize = 13.sp,
                            letterSpacing = 1.sp
                        )
                        Box(
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(period.uppercase(Locale.US), color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Text(
                        "Click below to invoke ValueAfrica's Google Gemini analytical engine and compile a comprehensive strategic executive summary briefing.",
                        fontSize = 11.sp,
                        color = TextLight.copy(alpha = 0.8f),
                        lineHeight = 15.sp
                    )

                    Button(
                        onClick = { viewModel.generateAdvancedAnalyticsReport() },
                        colors = ButtonDefaults.buttonColors(containerColor = Terracotta),
                        modifier = Modifier.fillMaxWidth().testTag("generate_report_button"),
                        shape = RoundedCornerShape(12.dp),
                        enabled = !viewModel.isAnalyticsReportLoading
                    ) {
                        if (viewModel.isAnalyticsReportLoading) {
                            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Mapping trends & diaspora data...", fontSize = 12.sp, color = Color.White)
                        } else {
                            Icon(Icons.Default.CheckCircle, "Report", tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Generate Detailed $period Summary Report", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Content Output
                    if (viewModel.analyticsCustomReportOutput.isNotBlank()) {
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f)),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text("Report Compilation Success", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = SunOchre)
                                    Icon(Icons.Default.CheckCircle, "Compiled", tint = LeafTeal, modifier = Modifier.size(14.dp))
                                }
                                Divider(color = Color.White.copy(alpha = 0.1f))
                                Text(
                                    text = viewModel.analyticsCustomReportOutput,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    color = TextLight
                                )
                            }
                        }
                    } else {
                        // Styled Placeholder
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "No Report Compiled Yet.\nSelect Weekly/Monthly above, then tap 'Generate' to draft.",
                                color = TextLight.copy(alpha = 0.5f),
                                fontSize = 11.sp,
                                lineHeight = 15.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        // Feature Design section: ValueAfrica Mobile App Blueprint
        item {
            Card(
                modifier = Modifier.fillMaxWidth().testTag("app_blueprint_card"),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Person, "Mobile Blueprint", tint = LeafTeal, modifier = Modifier.size(22.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Mobile App Strategic Blueprint",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Text(
                        "In compliance with Phase 2 of the corporate implementation calendar, here are the high-level blueprints and detailed sections designed for both Customer and Vendor mobile frameworks:",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        lineHeight = 15.sp
                    )

                    var selectedBlueprintType by remember { mutableStateOf("Customer") }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Customer", "Vendor").forEach { type ->
                            val isSel = selectedBlueprintType == type
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        if (isSel) LeafTeal.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .border(if (isSel) 1.dp else 0.dp, LeafTeal, RoundedCornerShape(8.dp))
                                    .clickable { selectedBlueprintType = type }
                                    .padding(vertical = 8.dp)
                                    .testTag("blueprint_tab_$type"),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$type Mobile App",
                                    color = if (isSel) LeafTeal else MaterialTheme.colorScheme.onSurface,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    // Render selected specs
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f))

                    if (selectedBlueprintType == "Customer") {
                        val custFeatures = listOf(
                            Pair("Browsing Products Engine", "Displays collections organized by geographic heritage provenance (e.g. Abuja, Kumasi). Incorporates immersive storytelling panels detailing the artisan's history and community, letting diaspora buyers connect emotionally to their purchase."),
                            Pair("Diaspora User Profiles", "Features a secure verification registry, preferred international shipping configurations, custom tax-diaspora calculators, and saved collections bookmarks."),
                            Pair("Escrow-Integrated Checkout", "Provides a secure gateway holding funds in trust-escrow, locking payment until tracking verifies intercontinental arrival."),
                            Pair("Intercontinental Order Tracking", "Provides real-time milestone integration with DHL Cargo. Tracks raw dispatch from Abuja sorting docks directly through customs clearance to destinations' doorsteps."),
                            Pair("Push Arrivals & Drops Alerts", "Drives customer retention via push notifications for limited artisan drop schedules, special cultural festival promotions, and category restocks.")
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            custFeatures.forEach { (name, desc) ->
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.CheckCircle, "Feature", tint = LeafTeal, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Terracotta)
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(desc, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f), lineHeight = 15.sp, modifier = Modifier.padding(start = 20.dp))
                                }
                            }
                        }
                    } else {
                        val vendFeatures = listOf(
                            Pair("Artisan Sizing & Catalog Vault", "Allows micro-artisans to list products directly. Includes description translation aids, offline item drafts, image formatting guides, and CAC credential binding."),
                            Pair("Ledger & Dynamic Analytics", "Mobile-optimized analytics dashboard reflecting real-time sales curves over time, customer diaspora geographical coordinates, and top listing evaluations."),
                            Pair("Logistics Sorting Dispatch", "Allows instant generation and print orders of DHL shipment labels, status updates to Abuja/Kumasi warehouses, and micro-cargo dispatch tracking."),
                            Pair("Integrated Customer DM Inbox", "Direct translation message hub connecting sellers with global buyers regarding bespoke custom orders and personalized craft specifications."),
                            Pair("Verification Compliance Status", "Integrates with CAC (Corporate Affairs Commission) API to view vetting stage, guild references, and export authority clearances of the business.")
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            vendFeatures.forEach { (name, desc) ->
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.CheckCircle, "Feature", tint = LeafTeal, modifier = Modifier.size(14.dp))
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(name, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Terracotta)
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(desc, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f), lineHeight = 15.sp, modifier = Modifier.padding(start = 20.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
