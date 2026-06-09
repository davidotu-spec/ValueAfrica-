import React, { useState, useMemo } from 'react';
import { 
  Globe, 
  Truck, 
  ShieldCheck, 
  ShoppingBag, 
  ChevronRight, 
  TrendingUp, 
  Users, 
  Smartphone, 
  Sparkles, 
  CheckCircle2, 
  MapPin, 
  Coins, 
  Star, 
  ArrowRight, 
  Check, 
  Calculator, 
  FileText 
} from 'lucide-react';

export default function App() {
  // Tabs and toggles state
  const [activeCraftTab, setActiveCraftTab] = useState('kente');
  const [mobileAppType, setMobileAppType] = useState('customer');
  const [analyticsPeriod, setAnalyticsPeriod] = useState('weekly');
  
  // Strategy Sim Calculator values
  const [cargoVolume, setCargoVolume] = useState(1500); // Monthly shipments
  const [orderValue, setOrderValue] = useState(120);     // Average Basket Size USD
  const [commissionRate, setCommissionRate] = useState(8); // Commission percentage

  // Calculator Math
  const simulationResults = useMemo(() => {
    const grossVolume = cargoVolume * orderValue;
    const adminIncome = grossVolume * (commissionRate / 100);
    const artisanShare = grossVolume - adminIncome;
    // Assume average salary injection of $200 per family sustained in West Africa
    const householdsSustained = Math.round(artisanShare / 200);

    return {
      grossVolume: grossVolume.toLocaleString('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }),
      adminIncome: adminIncome.toLocaleString('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }),
      artisanShare: artisanShare.toLocaleString('en-US', { style: 'currency', currency: 'USD', maximumFractionDigits: 0 }),
      householdsSustained
    };
  }, [cargoVolume, orderValue, commissionRate]);

  // Craft showcase list
  const crafts = {
    kente: {
      name: "Handwoven Premium Kente",
      origin: "Kumasi Craft Guild, Ghana",
      story: "Each thread in this primary masterpiece is dyed by hand with custom botanical extracts and woven on narrow horizontal looms. Representing spiritual maturity and heritage royalty, the patterns carry historic tribal meanings.",
      metric: "Sales: $4,580 (Fulfillment: 100%)",
      growth: "+45% Growth",
      price: "$185.00",
      vibe: "LeafTeal",
      imageText: "👑"
    },
    bronze: {
      name: "Emperor Benin Bronze Castings",
      origin: "Igun Street Guild, Abuja sorting hub",
      story: "Forged utilizing the historic lost-wax casting technique preserved over five centuries. This heavy ornamental casting features rigorous hand-carving in bronze alloy, bringing ancient West African courtly design to global collections.",
      metric: "Sales: $3,220 (Fulfillment: 96%)",
      growth: "+12% Growth",
      price: "$340.00",
      vibe: "terracotta",
      imageText: "🛡️"
    },
    shea: {
      name: "Organic Shea Butter - Gold Edition",
      origin: "Tamale Women's Cooperative, Ghana",
      story: "Premium grade-A cold-pressed shea oil formatted with wild-harvested nuts and enriched with organic baobab lipids. Packaged by hand in compostable wooden jars. Empowers 80+ rural women-led family structures.",
      metric: "Sales: $2,840 (Fulfillment: 98%)",
      growth: "+30% Growth",
      price: "$45.00",
      vibe: "sunOchre",
      imageText: "🍯"
    },
    aso: {
      name: "Aso Oke Minimalist Trench Coat",
      origin: "Oyo Weaver Cohort, Nigeria",
      story: "A stunning crossover marrying handloomed Yoruba strip-woven yarns with a clean minimalist Western silhouette. Features bold indigo natural pigment detailing and structural rain-resistant linen linings.",
      metric: "Sales: $1,810 (Fulfillment: 100%)",
      growth: "+75% Growth",
      price: "$290.00",
      vibe: "terracotta",
      imageText: "🧥"
    }
  };

  const activeCraft = crafts[activeCraftTab];

  // AI Generated Mock Reports
  const reports = {
    weekly: {
      title: "ValueAfrica Weekly Performance Report",
      highlights: [
        { label: "Partner Gross Revenue", value: "$2,710 USD" },
        { label: "Fulfilled Orders", value: "18 Complete Batches" },
        { label: "Active Diaspora Cohort", value: "+24% Growth" }
      ],
      insights: [
        "**Global Locations**: Customers in NY/MD/DC and London are driving 66% of the volume. High demand for custom home décor.",
        "**Logistics Hubs**: DHL consolidated air-cargo bulk shipments out of Abuja saved an average of 42% on raw parcel expenses.",
        "**Artisan Action**: Provide digital catalog templates for Kumasi weavers to match high-turnover summer demand."
      ]
    },
    monthly: {
      title: "ValueAfrica Monthly Strategic Insight Summary",
      highlights: [
        { label: "Partner Gross Revenue", value: "$26,600 USD" },
        { label: "Fulfilled Orders", value: "142 Shipments" },
        { label: "Active Diaspora Cohort", value: "+38% Growth" }
      ],
      insights: [
        "**Provenances**: Handloomed Textiles and bronze figures claim 71% of gross payouts. Micro-producers in Abuja have expanded capacity by 15%.",
        "**Logistics Nodes**: Air sorting bridges between Abuja and Kumasi are operational. Customs integration with EU hubs completed.",
        "**Artisan Action**: Secure local raw brass inputs in Abuja to insulate producers against international metals fluctuation."
      ]
    }
  };

  const activeReport = reports[analyticsPeriod];

  return (
    <div className="min-h-screen flex flex-col bg-[#FCF9F5]">
      
      {/* 1. Header / Navigation */}
      <nav className="sticky top-0 z-50 bg-[#FCF9F5]/90 backdrop-blur-md border-b border-[#2D241E]/5 transition-all">
        <div className="max-w-7xl mx-auto px-6 h-18 flex items-center justify-between">
          <div className="flex items-center space-x-3">
            <div className="w-10 h-10 rounded-xl bg-[#B55D21] flex items-center justify-center text-white font-serif font-extrabold text-xl shadow-md">
              VA
            </div>
            <div>
              <span className="font-serif font-extrabold text-[#B55D21] text-lg block leading-none">ValueAfrica</span>
              <span className="text-[10px] tracking-wider text-[#2D241E]/60 uppercase font-semibold">Innovation Hub</span>
            </div>
          </div>
          
          <div className="hidden md:flex items-center space-x-8 font-semibold text-xs text-[#2D241E]/80">
            <a href="#mission" className="hover:text-[#B55D21] transition">Mission</a>
            <a href="#pillars" className="hover:text-[#B55D21] transition">Core Pillars</a>
            <a href="#calculator" className="hover:text-[#B55D21] transition">Impact Calculator</a>
            <a href="#showcase" className="hover:text-[#B55D21] transition">Crafts Showcase</a>
            <a href="#blueprints" className="hover:text-[#B55D21] transition">App Blueprint</a>
            <a href="#analytics" className="hover:text-[#B55D21] transition">AI Analytics</a>
          </div>

          <div>
            <a 
              href="#join" 
              className="bg-[#2D4F1E] hover:bg-[#1B3012] text-white px-5 py-2.5 rounded-xl text-xs font-bold transition shadow-sm inline-flex items-center space-x-1.5"
            >
              <span>Secure Hub Entry</span>
              <ChevronRight className="w-3.5 h-3.5" />
            </a>
          </div>
        </div>
      </nav>

      {/* 2. Hero Section */}
      <header className="relative pt-12 pb-24 px-6 overflow-hidden">
        <div className="max-w-7xl mx-auto grid md:grid-cols-12 gap-12 items-center">
          
          {/* Hero Left Content */}
          <div className="md:col-span-7 flex flex-col justify-center space-y-8">
            <div className="inline-flex items-center space-x-2 bg-[#DDA15E]/15 border border-[#DDA15E]/30 px-3.5 py-1.5 rounded-full text-[#B55D21] text-xs font-bold leading-none w-fit">
              <Sparkles className="w-3 text-[#DDA15E]" />
              <span>BRIDGING AFRICAN SMEs WITH THE DIASPORA</span>
            </div>
            
            <h1 className="font-serif text-4xl sm:text-5xl lg:text-6xl text-[#2D241E] leading-[1.1] font-bold">
              Direct E-Commerce & <span className="text-[#B55D21]">Frictionless Logistics</span> for Authentic African Craftsmanship
            </h1>
            
            <p className="text-[#2D241E]/80 text-base sm:text-lg max-w-xl leading-relaxed font-normal">
              ValueAfrica is a modern innovation infrastructure hub operating out of Abuja. We empower indigenous creators to overcome structural fragmentation by providing verified digital storefronts, local trade validation, and DHL-backed global supply routes.
            </p>
            
            <div className="flex flex-col sm:flex-row gap-4">
              <a 
                href="#calculator" 
                className="bg-[#B55D21] hover:bg-[#8D4311] text-white text-center px-7 py-3.5 rounded-xl font-bold text-sm shadow-md transition flex items-center justify-center space-x-2"
              >
                <span>Simulate Local Impact</span>
                <ArrowRight className="w-4 h-4" />
              </a>
              <a 
                href="#blueprints" 
                className="bg-white border-2 border-[#2D241E]/10 hover:border-[#2D241E]/30 text-[#2D241E] text-center px-7 py-3.5 rounded-xl font-bold text-sm transition flex items-center justify-center space-x-2"
              >
                <span>Read App Blueprint</span>
              </a>
            </div>

            {/* Micro Badges */}
            <div className="pt-4 border-t border-[#2D241E]/10 grid grid-cols-3 gap-4 max-w-md">
              <div>
                <span className="font-serif text-2xl font-bold text-[#B55D21] block">Abuja & Kumasi</span>
                <span className="text-[10px] uppercase font-bold text-[#2D241E]/55">Logistics Sorting Hubs</span>
              </div>
              <div>
                <span className="font-serif text-2xl font-bold text-[#2D4F1E] block">CAC Vetted</span>
                <span className="text-[10px] uppercase font-bold text-[#2D241E]/55">100% Secure Guilds</span>
              </div>
              <div>
                <span className="font-serif text-2xl font-bold text-[#DDA15E] block">DHL Global</span>
                <span className="text-[10px] uppercase font-bold text-[#2D241E]/55">Direct Bulk Routes</span>
              </div>
            </div>
          </div>

          {/* Hero Right Graphic Container */}
          <div className="md:col-span-5 relative">
            <div className="w-full aspect-square relative flex items-center justify-center">
              {/* Abs Geometric background elements */}
              <div className="absolute inset-0 bg-[#2D4F1E]/5 rounded-3xl transform rotate-3 scale-105"></div>
              <div className="absolute inset-0 bg-[#B55D21]/5 rounded-3xl transform -rotate-3"></div>
              
              {/* Designer Card Canvas */}
              <div className="relative bg-white border border-[#2D241E]/10 rounded-2xl w-full p-8 shadow-xl flex flex-col justify-between space-y-6">
                <div className="flex justify-between items-center">
                  <div className="flex items-center space-x-2">
                    <span className="w-2.5 h-2.5 rounded-full bg-[#2D4F1E] animate-pulse"></span>
                    <span className="text-[10px] font-bold text-[#2D241E]/60 uppercase tracking-wider">Live Hub Metrics</span>
                  </div>
                  <span className="text-xs bg-[#B55D21]/15 text-[#B55D21] px-2.5 py-1 rounded-full font-bold">Nigeria & Ghana</span>
                </div>

                <div className="space-y-4">
                  <div className="border border-[#2D241E]/5 bg-[#FCF9F5] p-3.5 rounded-xl">
                    <span className="text-[10px] text-[#2D241E]/55 font-bold uppercase block">Frictionless Routing</span>
                    <div className="flex justify-between items-center mt-1">
                      <span className="text-sm font-bold">Abuja Dock → NY Gateway</span>
                      <span className="text-xs font-bold text-[#2D4F1E]">DHL Priority Air</span>
                    </div>
                  </div>

                  <div className="border border-[#2D241E]/5 bg-[#FCF9F5] p-3.5 rounded-xl">
                    <span className="text-[10px] text-[#2D241E]/55 font-bold uppercase block">Compliance Engine</span>
                    <div className="flex justify-between items-center mt-1">
                      <span className="text-sm font-bold">CAC Verification Service</span>
                      <span className="text-xs font-bold text-[#2D4F1E] flex items-center">
                        <ShieldCheck className="w-3.5 h-3.5 mr-1" /> Checked
                      </span>
                    </div>
                  </div>
                </div>

                <div className="pt-4 border-t border-[#2D241E]/5 flex justify-between items-center">
                  <div>
                    <span className="text-[10px] text-[#2D241E]/50 font-bold block">TOTAL COMMITTED VOLUME</span>
                    <span className="font-serif text-xl font-bold text-[#2D241E]">$124,500+ USD</span>
                  </div>
                  <div className="bg-[#B55D21] p-3 rounded-xl text-white">
                    <Globe className="w-5 h-5" />
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </header>

      {/* 3. Corporate Mission & Vision */}
      <section id="mission" className="bg-[#F4EBE2] py-20 px-6 border-y border-[#2D241E]/5">
        <div className="max-w-7xl mx-auto">
          <div className="text-center max-w-2xl mx-auto space-y-4 mb-16">
            <span className="text-xs font-extrabold text-[#B55D21] tracking-wider uppercase block">THE CORPORATE COMPASS</span>
            <h2 className="font-serif text-3xl sm:text-4xl font-bold text-[#2D241E]">ValueAfrica Brand Intentions</h2>
            <p className="text-[#2D241E]/75 text-sm leading-relaxed">
              We directly address the structural bottleneck of international market access by serving as a secure pipeline for premium goods, backing local creators with absolute trust verification.
            </p>
          </div>

          <div className="grid md:grid-cols-2 gap-8 lg:gap-12">
            {/* Vision Card */}
            <div className="bg-[#B55D21] rounded-2xl p-8 text-white relative overflow-hidden shadow-lg flex flex-col justify-between min-h-[250px]">
              <div className="absolute right-0 top-0 w-32 h-32 bg-white/5 rounded-full transform translate-x-12 -translate-y-12"></div>
              <div>
                <div className="w-12 h-12 rounded-xl bg-white/15 flex items-center justify-center mb-6">
                  <Star className="w-6 h-6 text-[#DDA15E]" />
                </div>
                <h3 className="font-serif text-2xl font-bold mb-3">Our Vision</h3>
                <p className="text-white/90 text-sm leading-relaxed max-w-md italic font-light">
                  "To become Africa’s most trusted digital marketplace which champions fair value and global reach for indigenous craftsmanship."
                </p>
              </div>
              <span className="text-white/40 uppercase tracking-widest text-[9px] font-bold mt-8">COMMITTED TO INTEGRITY</span>
            </div>

            {/* Mission Card */}
            <div className="bg-[#2D4F1E] rounded-2xl p-8 text-white relative overflow-hidden shadow-lg flex flex-col justify-between min-h-[250px]">
              <div className="absolute right-0 top-0 w-32 h-32 bg-white/5 rounded-full transform translate-x-12 -translate-y-12"></div>
              <div>
                <div className="w-12 h-12 rounded-xl bg-white/15 flex items-center justify-center mb-6">
                  <Globe className="w-6 h-6 text-[#DDA15E]" />
                </div>
                <h3 className="font-serif text-2xl font-bold mb-3">Our Mission</h3>
                <p className="text-white/90 text-sm leading-relaxed max-w-md font-light">
                  "To empower African creators and SMEs via digital visibility, comprehensive brand packaging, and frictionless logistics infrastructure."
                </p>
              </div>
              <span className="text-white/40 uppercase tracking-widest text-[9px] font-bold mt-8">BRIDGING REGIONAL ISOLATION</span>
            </div>
          </div>
        </div>
      </section>

      {/* 4. Core Solutions / Bento Grid */}
      <section id="pillars" className="py-24 px-6 max-w-7xl mx-auto">
        <div className="flex flex-col md:flex-row md:items-end justify-between gap-6 mb-16">
          <div className="max-w-xl space-y-4">
            <span className="text-xs font-extrabold text-[#2D4F1E] tracking-wider uppercase block">SOLVING CONTINENTAL FRAGMENTATION</span>
            <h2 className="font-serif text-3xl sm:text-4xl font-bold text-[#2D241E]">The Infrastructure Framework</h2>
          </div>
          <p className="text-[#2D241E]/75 text-sm max-w-md leading-relaxed">
            ValueAfrica functions as a secure routing and verification channel. We handle the complex compliance, security, and cargo consolidation steps so creators can focus solely on design.
          </p>
        </div>

        <div className="grid md:grid-cols-6 gap-6">
          
          {/* Bento Cell 1: Logistics */}
          <div className="md:col-span-4 bg-white border border-[#2D241E]/10 p-8 rounded-2xl shadow-sm hover:shadow-md transition flex flex-col justify-between gap-6">
            <div className="space-y-4">
              <div className="w-10 h-10 rounded-xl bg-[#B55D21]/10 flex items-center justify-center text-[#B55D21]">
                <Truck className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-lg text-[#2D241E]">Consolidated Warehousing & Abuja-Kumasi Sorting Docks</h3>
              <p className="text-sm text-[#2D241E]/75 leading-relaxed">
                By aggregating individual orders at our sorting facilities in Abuja (Nigeria) and Kumasi (Ghana), we mitigate high costs. We secure deep bulk discounts with global air cargo partners (DHL), routing multiple parcels under a single consolidated clearance ticket directly to destination countries.
              </p>
            </div>
            <div className="flex items-center space-x-2 text-xs font-bold text-[#B55D21] pt-2">
              <span>Reviewing custom transit corridors</span>
              <ChevronRight className="w-4 h-4" />
            </div>
          </div>

          {/* Bento Cell 2: Vetting */}
          <div className="md:col-span-2 bg-[#2D4F1E] text-white p-8 rounded-2xl shadow-sm hover:shadow-md transition flex flex-col justify-between gap-6">
            <div className="space-y-4">
              <div className="w-10 h-10 rounded-xl bg-white/10 flex items-center justify-center text-[#DDA15E]">
                <ShieldCheck className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-lg">Rigorous Guild Vetting</h3>
              <p className="text-xs text-white/85 leading-relaxed">
                All artisans are thoroughly vetted before joining. We verify business registrations with the Corporate Affairs Commission (CAC) and cross-reference them with regional trade guilds, ensuring 100% provenance authenticity and shielding buyers from fraud.
              </p>
            </div>
            <div className="border-t border-white/10 pt-4 flex items-center justify-between text-[11px] uppercase font-bold text-[#DDA15E]">
              <span>Escrow Gateway secured</span>
              <Check className="w-4 h-4" />
            </div>
          </div>

          {/* Bento Cell 3: Local to Global */}
          <div className="md:col-span-2 bg-[#F4EBE2] border border-[#2D241E]/10 p-8 rounded-2xl shadow-sm hover:shadow-md transition flex flex-col justify-between gap-6">
            <div className="space-y-4">
              <div className="w-10 h-10 rounded-xl bg-[#DDA15E]/20 flex items-center justify-center text-[#B55D21]">
                <Globe className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-lg text-[#2D241E]">Unlocking the Diaspora</h3>
              <p className="text-xs text-[#2D241E]/80 leading-relaxed">
                The massive African Diaspora in the US, UK, Canada, and EU represents a multi-billion dollar market eager for high-grade indigenous products. ValueAfrica serves as an institutional trust bridge that resolves high transaction frictions.
              </p>
            </div>
            <span className="text-[10px] font-extrabold uppercase text-[#2D241E]/50">Targeting High-Spending hubs</span>
          </div>

          {/* Bento Cell 4: App Storefronts */}
          <div className="md:col-span-4 bg-white border border-[#2D241E]/10 p-8 rounded-2xl shadow-sm hover:shadow-md transition flex flex-col justify-between gap-6">
            <div className="space-y-4">
              <div className="w-10 h-10 rounded-xl bg-[#2D4F1E]/10 flex items-center justify-center text-[#2D4F1E]">
                <ShoppingBag className="w-5 h-5" />
              </div>
              <h3 className="font-semibold text-lg text-[#2D241E]">Turnkey E-Commerce Infrastructure for Micro-SMEs</h3>
              <p className="text-sm text-[#2D241E]/75 leading-relaxed">
                Many micro-artisans fail due to a lack of technical expertise. ValueAfrica equips they with responsive digital catalog tools, automated invoice generators, and direct payment routing (local accounts directly settled in Naira/Cedis based on USD trades), immediately transforming any phone into a global storefront.
              </p>
            </div>
            <span className="text-[10px] font-extrabold uppercase text-[#2D4F1E]">Fully integrated with customer portals</span>
          </div>

        </div>
      </section>

      {/* 5. Strategic Commission & Local Impact Modeler [Interactive Calculator] */}
      <section id="calculator" className="bg-[#2D241E] text-white py-24 px-6 border-y border-[#B55D21]/15">
        <div className="max-w-7xl mx-auto">
          <div className="grid lg:grid-cols-12 gap-12 items-center">
            
            {/* Calculator Left */}
            <div className="lg:col-span-5 space-y-6">
              <div className="inline-flex items-center space-x-2 bg-white/10 px-3 py-1 rounded-full text-[#DDA15E] text-xs font-bold leading-none">
                <Calculator className="w-3.5 h-3.5" />
                <span>DYNAMIC LOCAL IMPACT SIMULATOR</span>
              </div>
              <h2 className="font-serif text-3xl sm:text-4xl font-bold">Model Our Cooperative Cashflows</h2>
              <p className="text-white/70 text-sm leading-relaxed">
                Consistent with the Strategy metrics inside our mobile dashboard, use this calculator to simulate how hub-sorted transaction flows generate revenue and sustain local households.
              </p>
              
              <div className="space-y-2 text-xs bg-white/5 border border-white/10 p-4 rounded-xl text-white/80">
                <p className="font-semibold text-white">How this works:</p>
                <p>We aggregate shipping volumes via Abuja and Kumasi. ValueAfrica secures a minor transactional commission (e.g. 5-15%) for platform upkeep, and routes the remainder directly to verified artisans, providing sustainable family incomes.</p>
              </div>
            </div>

            {/* Calculator Control Panel (Right) */}
            <div className="lg:col-span-7 bg-white text-[#2D241E] rounded-2xl p-8 shadow-xl grid md:grid-cols-2 gap-8 border border-white/10">
              
              {/* Sliders Side */}
              <div className="space-y-6">
                <h3 className="font-bold text-sm text-[#B55D21] uppercase tracking-wider flex items-center">
                  <Coins className="w-4 h-4 mr-1.5" /> Simulation Constants
                </h3>
                
                {/* Slider 1: Shipments Volume */}
                <div className="space-y-2">
                  <div className="flex justify-between items-center text-xs font-semibold">
                    <span>Monthly Shipments</span>
                    <span className="text-[#2D4F1E] font-extrabold">{cargoVolume.toLocaleString()} units</span>
                  </div>
                  <input 
                    type="range" 
                    min="100" 
                    max="10000" 
                    step="100"
                    value={cargoVolume}
                    onChange={(e) => setCargoVolume(Number(e.target.value))}
                    className="w-full h-1.5 bg-gray-200 rounded-lg appearance-none cursor-pointer accent-[#B55D21]"
                  />
                  <div className="flex justify-between text-[10px] text-gray-400">
                    <span>100</span>
                    <span>10,000 max</span>
                  </div>
                </div>

                {/* Slider 2: Average Basket Size */}
                <div className="space-y-2">
                  <div className="flex justify-between items-center text-xs font-semibold">
                    <span>Avg. Basket Size (USD)</span>
                    <span className="text-[#2D4F1E] font-extrabold">${orderValue}</span>
                  </div>
                  <input 
                    type="range" 
                    min="20" 
                    max="500" 
                    step="10"
                    value={orderValue}
                    onChange={(e) => setOrderValue(Number(e.target.value))}
                    className="w-full h-1.5 bg-gray-200 rounded-lg appearance-none cursor-pointer accent-[#B55D21]"
                  />
                  <div className="flex justify-between text-[10px] text-gray-400">
                    <span>$20</span>
                    <span>$500 max</span>
                  </div>
                </div>

                {/* Slider 3: Commission rate */}
                <div className="space-y-2">
                  <div className="flex justify-between items-center text-xs font-semibold">
                    <span>ValueAfrica Commission</span>
                    <span className="text-[#2D4F1E] font-extrabold">{commissionRate}%</span>
                  </div>
                  <input 
                    type="range" 
                    min="5" 
                    max="15" 
                    step="1"
                    value={commissionRate}
                    onChange={(e) => setCommissionRate(Number(e.target.value))}
                    className="w-full h-1.5 bg-gray-200 rounded-lg appearance-none cursor-pointer accent-[#B55D21]"
                  />
                  <div className="flex justify-between text-[10px] text-gray-400">
                    <span>5%</span>
                    <span>15% limit</span>
                  </div>
                </div>
              </div>

              {/* Results Output Side */}
              <div className="bg-[#FCF9F5] rounded-xl p-6 flex flex-col justify-between border border-[#2D241E]/5 space-y-6">
                <div>
                  <h4 className="text-[10px] font-bold uppercase tracking-wider text-[#2D241E]/50 mb-4">Calculated Outputs</h4>
                  
                  <div className="space-y-3">
                    <div className="flex justify-between items-baseline border-b border-gray-200/50 pb-2">
                      <span className="text-xs text-gray-500">Gross Network Trade</span>
                      <span className="font-bold text-sm">{simulationResults.grossVolume}</span>
                    </div>

                    <div className="flex justify-between items-baseline border-b border-gray-200/50 pb-2">
                      <span className="text-xs text-gray-500">Platform Share ({commissionRate}%)</span>
                      <span className="font-bold text-sm text-[#B55D21]">{simulationResults.adminIncome}</span>
                    </div>

                    <div className="flex justify-between items-baseline pt-1">
                      <span className="text-xs text-gray-500 font-medium">Artisan Direct Payout</span>
                      <span className="font-extrabold text-base text-[#2D4F1E]">{simulationResults.artisanShare}</span>
                    </div>
                  </div>
                </div>

                <div className="bg-[#2D4F1E]/10 border border-[#2D4F1E]/20 p-4 rounded-lg">
                  <span className="text-[9px] font-bold text-[#2D4F1E] uppercase tracking-wider block mb-1">Local Social Dividends</span>
                  <p className="font-serif text-2xl font-black text-[#2D4F1E] leading-tight">
                    {simulationResults.householdsSustained} <span className="font-sans text-xs font-bold uppercase text-gray-600 block">Households Sustained</span>
                  </p>
                  <p className="text-[9px] text-[#2D241E]/75 mt-1">Based on monthly average West-African artisan craft cooperative salary injection of $200.</p>
                </div>
              </div>

            </div>

          </div>
        </div>
      </section>

      {/* 6. Featured Crafts Showcase Slider */}
      <section id="showcase" className="py-24 px-6 max-w-7xl mx-auto">
        <div className="text-center max-w-2xl mx-auto space-y-4 mb-16">
          <span className="text-xs font-extrabold text-[#B55D21] tracking-wider uppercase block">CURATED PROVENANCES</span>
          <h2 className="font-serif text-3xl sm:text-4xl font-bold text-[#2D241E]">Authentic Product Showcases</h2>
          <p className="text-[#2D241E]/70 text-sm">
            Discover the stories and logistics metrics behind the premium crafts managed by our regional cooperatives.
          </p>
        </div>

        {/* Tab Header Selector */}
        <div className="flex flex-wrap justify-center gap-3 mb-10">
          {Object.keys(crafts).map((key) => (
            <button
              key={key}
              onClick={() => setActiveCraftTab(key)}
              className={`px-5 py-2.5 rounded-xl font-bold text-xs transition border cursor-pointer ${
                activeCraftTab === key 
                  ? 'bg-[#B55D21] border-[#B55D21] text-white shadow-md' 
                  : 'bg-white border-[#2D241E]/10 text-[#2D241E]/80 hover:bg-gray-50 hover:border-[#2D241E]/30'
              }`}
            >
              {crafts[key].name}
            </button>
          ))}
        </div>

        {/* Selected Product Card Detail Layout */}
        <div className="bg-white border border-[#2D241E]/10 rounded-2xl overflow-hidden shadow-sm grid md:grid-cols-12 max-w-5xl mx-auto">
          {/* Left Visual Placeholder */}
          <div className="md:col-span-4 bg-[#F4EBE2] p-8 flex items-center justify-center min-h-[220px] relative">
            <span className="text-8xl select-none">{activeCraft.imageText}</span>
            <div className="absolute top-4 left-4 bg-white/80 border border-[#2D241E]/10 backdrop-blur-sm px-3 py-1 rounded-full text-[10px] font-bold">
              {activeCraft.origin}
            </div>
          </div>
          
          {/* Right Product Data */}
          <div className="md:col-span-8 p-8 flex flex-col justify-between space-y-6">
            <div className="space-y-4">
              <div className="flex flex-wrap justify-between items-baseline gap-2">
                <h3 className="font-serif text-2xl font-bold text-[#2D241E]">{activeCraft.name}</h3>
                <span className="font-serif text-xl font-black text-[#B55D21]">{activeCraft.price}</span>
              </div>
              <p className="text-[#2D241E]/75 text-sm leading-relaxed">{activeCraft.story}</p>
            </div>

            <div className="border-t border-[#2D241E]/5 pt-4 flex flex-wrap justify-between items-center gap-4 text-xs font-semibold">
              <div className="flex items-center space-x-2">
                <span className="w-2 h-2 rounded-full bg-[#2D4F1E]"></span>
                <span className="text-[#2D241E]/80">{activeCraft.metric}</span>
              </div>
              <div className="bg-[#2D4F1E]/10 text-[#2D4F1E] px-3 py-1 rounded-full text-[10px] font-bold">
                {activeCraft.growth}
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* 7. Strategic Mobile Application Blueprints (VENDORS & CUSTOMERS) */}
      <section id="blueprints" className="bg-[#F4EBE2] py-24 px-6 border-y border-[#2D241E]/5">
        <div className="max-w-7xl mx-auto">
          <div className="text-center max-w-2xl mx-auto space-y-4 mb-16">
            <span className="text-xs font-extrabold text-[#2D4F1E] tracking-wider uppercase block">PHASE 2 COMPANION IMPLEMENTATION</span>
            <h2 className="font-serif text-3xl sm:text-4xl font-bold text-[#2D241E]">Mobile Application Specs</h2>
            <p className="text-[#2D241E]/70 text-sm">
              Review our complete strategic features engineered for both Customer and Vendor mobile platforms.
            </p>
          </div>

          <div className="max-w-4xl mx-auto bg-white border border-[#2D241E]/10 rounded-2xl p-6 sm:p-8 shadow-md">
            
            {/* Toggle App Selector */}
            <div className="flex bg-[#FCF9F5] p-1.5 rounded-xl border border-[#2D241E]/5 mb-8 max-w-md mx-auto">
              <button
                onClick={() => setMobileAppType('customer')}
                className={`flex-1 py-3 text-center rounded-lg text-xs font-bold transition flex items-center justify-center space-x-2 cursor-pointer ${
                  mobileAppType === 'customer' 
                  ? 'bg-[#2D4F1E] text-white shadow-sm' 
                  : 'text-[#2D241E]/70 hover:text-[#2D241E] hover:bg-gray-50'
                }`}
              >
                <Smartphone className="w-3.5 h-3.5" />
                <span>Customer App Specs</span>
              </button>
              <button
                onClick={() => setMobileAppType('vendor')}
                className={`flex-1 py-3 text-center rounded-lg text-xs font-bold transition flex items-center justify-center space-x-2 cursor-pointer ${
                  mobileAppType === 'vendor' 
                  ? 'bg-[#2D4F1E] text-white shadow-sm' 
                  : 'text-[#2D241E]/70 hover:text-[#2D241E] hover:bg-gray-50'
                }`}
              >
                <Users className="w-3.5 h-3.5" />
                <span>Vendor App Specs</span>
              </button>
            </div>

            {/* Spec Output */}
            {mobileAppType === 'customer' ? (
              <div className="space-y-6">
                <div className="pb-4 border-b border-[#2D241E]/5 flex justify-between items-center">
                  <span className="text-sm font-extrabold text-[#B55D21]">COOPERATIVE DIASPORA INTERFACE</span>
                  <span className="text-[10px] bg-[#2D4F1E]/15 text-[#2D4F1E] px-2.5 py-1 rounded font-bold uppercase">Ready to Build</span>
                </div>
                
                <div className="grid sm:grid-cols-2 gap-6">
                  {/* Item 1 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Browsing & Heritage stories
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Explores curations by regional sorting blocks (Abuja, Kumasi). Each listing incorporates rich historical storytelling, explaining the creator's guild origin and techniques.
                    </p>
                  </div>
                  {/* Item 2 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Diaspora Profiles Registry
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Integrated tax declarations, international shipping configurations, global payment vault cards, and historical order receipts tailored for the Western Diaspora.
                    </p>
                  </div>
                  {/* Item 3 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Trust-Escrow Checkout
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Ensures buyer security by holding funds in escrow trust, locking vendor payouts until real-time tracking confirms arrival at international customs.
                    </p>
                  </div>
                  {/* Item 4 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Real-time Tracking (DHL)
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Direct deep API mapping with DHL Cargo tracking. Features step-by-step visibility from micro-sorting docks in Abuja to doorstep clearance in NY/Toronto/London.
                    </p>
                  </div>
                </div>
                
                <div className="pt-4 border-t border-[#2D241E]/5 text-center">
                  <p className="text-xs font-semibold text-[#2D4F1E]/80 inline-flex items-center">
                    <Sparkles className="w-3.5 h-3.5 mr-2 text-[#DDA15E]" />
                    Push notifications are fully integrated to alert users about special artisan restocks and drop times!
                  </p>
                </div>
              </div>
            ) : (
              <div className="space-y-6">
                <div className="pb-4 border-b border-[#2D241E]/5 flex justify-between items-center">
                  <span className="text-sm font-extrabold text-[#B55D21]">ARTISAN BUSINESS LEDGER</span>
                  <span className="text-[10px] bg-[#2D4F1E]/15 text-[#2D4F1E] px-2.5 py-1 rounded font-bold uppercase">Ready to Build</span>
                </div>

                <div className="grid sm:grid-cols-2 gap-6">
                  {/* Item 1 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Catalogue & Sizing Vaults
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Equips weavers and sculptors to easily list dimensions, variations, translation details, and catalog image uploads via offline-friendly cash profiles.
                    </p>
                  </div>
                  {/* Item 2 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Financial ledger & Analytics
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      A visual dashboard outlining sales graphs, customer hotspots coordinates, and comparative views to identify which craft types are driving active conversions.
                    </p>
                  </div>
                  {/* Item 3 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> Cargo Sorting Dispatch
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Generates local pickup vouchers, instantly prints DHL tracking labels, and coordinates transport details with Abuja and Kumasi consolidated warehouses.
                    </p>
                  </div>
                  {/* Item 4 */}
                  <div className="space-y-2">
                    <h4 className="font-bold text-sm text-[#2D241E] flex items-center">
                      <CheckCircle2 className="w-4 h-4 mr-2 text-[#2D4F1E]" /> CAC Compliance & Inbox
                    </h4>
                    <p className="text-xs text-gray-500 leading-relaxed pl-6">
                      Tracks verification status with the local Corporate Affairs Commission registers, checks trade guild references, and hosts an integrated buyer translation inbox.
                    </p>
                  </div>
                </div>

                <div className="pt-4 border-t border-[#2D241E]/5 text-center">
                  <p className="text-xs font-semibold text-[#2D4F1E]/80 inline-flex items-center">
                    <Sparkles className="w-3.5 h-3.5 mr-2 text-[#DDA15E]" />
                    Features a built-in escrow notifications listener to alert creators instantly when payment is securely escrowed.
                  </p>
                </div>
              </div>
            )}

          </div>
        </div>
      </section>

      {/* 8. AI Summary Performance Reports Preview Section */}
      <section id="analytics" className="py-24 px-6 max-w-7xl mx-auto">
        <div className="grid lg:grid-cols-12 gap-12 items-center">
          
          {/* Left Text */}
          <div className="lg:col-span-5 space-y-6">
            <span className="text-xs font-extrabold text-[#B55D21] tracking-wider uppercase block">GOOGLE GEMINI API INTEGRATED</span>
            <h2 className="font-serif text-3xl sm:text-4xl font-bold text-[#2D241E]">Automated AI Executive Performance Hub</h2>
            <p className="text-[#2D241E]/75 text-sm leading-relaxed">
              Our mobile platform harnesses Google Gemini's advanced models to synthesize transactional history, sorting statistics, and geographical diaspora buyers to automatically draft comprehensive performance summaries.
            </p>
            <p className="text-[#2D241E]/75 text-sm leading-relaxed">
              Tap the buttons to preview how the AI digests complex datasets and returns critical microeconomic strategic suggestions for the Abuja and Kumasi guilds.
            </p>
          </div>

          {/* Right Live Mock Console */}
          <div className="lg:col-span-7 bg-[#2D241E] rounded-2xl p-6 sm:p-8 text-white shadow-xl relative border border-white/5">
            <div className="flex justify-between items-center pb-4 border-b border-white/10 mb-6">
              <div className="flex items-center space-x-2">
                <div className="w-2.5 h-2.5 rounded-full bg-[#DDA15E]"></div>
                <span className="text-xs uppercase font-extrabold tracking-wider text-white/70">MOCKED REPORT GENERATOR</span>
              </div>
              
              {/* Report selection tabs */}
              <div className="flex bg-white/5 border border-white/10 rounded-lg p-1 text-xs">
                <button
                  onClick={() => setAnalyticsPeriod('weekly')}
                  className={`px-3 py-1.5 rounded-md font-bold transition cursor-pointer ${
                    analyticsPeriod === 'weekly' ? 'bg-[#B55D21] text-white' : 'text-white/70 hover:text-white'
                  }`}
                >
                  Weekly
                </button>
                <button
                  onClick={() => setAnalyticsPeriod('monthly')}
                  className={`px-3 py-1.5 rounded-md font-bold transition cursor-pointer ${
                    analyticsPeriod === 'monthly' ? 'bg-[#B55D21] text-white' : 'text-white/70 hover:text-white'
                  }`}
                >
                  Monthly
                </button>
              </div>
            </div>

            {/* Generated Output */}
            <div className="space-y-6">
              <div>
                <span className="text-[10px] font-bold uppercase text-[#DDA15E] block">INTELLIGENT INSIGHT HEADER</span>
                <h3 className="font-serif text-xl font-bold mt-1 text-white">{activeReport.title}</h3>
              </div>

              {/* Stats highlights */}
              <div className="grid grid-cols-3 gap-3 bg-white/5 p-4 rounded-xl border border-white/10">
                {activeReport.highlights.map((h, i) => (
                  <div key={i}>
                    <span className="text-[9px] uppercase font-bold text-white/50 block">{h.label}</span>
                    <span className="text-sm font-black text-white">{h.value}</span>
                  </div>
                ))}
              </div>

              {/* Bullet insights */}
              <div className="space-y-3">
                <span className="text-[10px] font-bold uppercase text-[#DDA15E] block">AI STRATEGY DIRECTIVES</span>
                <div className="space-y-2.5 text-xs text-white/80 leading-relaxed">
                  {activeReport.insights.map((ins, i) => {
                    const cleanText = ins.replace(/\*\*/g, '');
                    return (
                      <div key={i} className="flex items-start">
                        <span className="text-[#B55D21] font-bold mr-2">•</span>
                        <p>{cleanText}</p>
                      </div>
                    );
                  })}
                </div>
              </div>
            </div>

          </div>

        </div>
      </section>

      {/* 9. Join / Footer Call to action */}
      <section id="join" className="bg-[#2D4F1E] text-white py-24 px-6 relative overflow-hidden">
        <div className="absolute inset-0 bg-[#B55D21]/5 mix-blend-overlay"></div>
        <div className="max-w-4xl mx-auto text-center relative z-10 space-y-8">
          <span className="text-xs font-extrabold text-[#DDA15E] tracking-wider uppercase block">JOIN THE COOPERATIVE NETWORK</span>
          <h2 className="font-serif text-4xl sm:text-5xl font-bold">Ready to Elevate African Craftsmanship?</h2>
          <p className="text-white/80 text-sm max-w-xl mx-auto leading-relaxed">
            Whether you are a verified artisan cohort in Abuja/Kumasi, an SME seeking digital visibility, or a global buyer in the Diaspora desiring curated, authenticated heritage crafts: join ValueAfrica today.
          </p>

          <div className="max-w-md mx-auto flex flex-col sm:flex-row gap-3">
            <input 
              type="email" 
              placeholder="Enter your email" 
              className="px-5 py-3.5 bg-white text-[#2D241E] rounded-xl text-xs font-semibold focus:outline-none focus:ring-2 focus:ring-[#B55D21] flex-1"
            />
            <button 
              className="bg-[#B55D21] hover:bg-[#8D4311] text-white transition px-6 py-3.5 rounded-xl text-xs font-extrabold whitespace-nowrap shadow-md cursor-pointer"
            >
              Get Secured Updates
            </button>
          </div>

          <p className="text-[10px] text-white/50">By joining, you agree to our CAC/guild peer compliance rules. Secure 256-bit authentication active.</p>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-[#1E1815] text-[#FCF9F5]/60 py-12 px-6 text-xs border-t border-white/5">
        <div className="max-w-7xl mx-auto flex flex-col md:flex-row justify-between items-center gap-6">
          <div className="flex items-center space-x-3">
            <div className="w-8 h-8 rounded-lg bg-[#B55D21] flex items-center justify-center text-white font-serif font-black text-sm">
              VA
            </div>
            <div>
              <span className="font-serif font-bold text-white block">ValueAfrica</span>
              <span className="text-[9px] uppercase font-bold tracking-wider opacity-60">SME Innovation Hub</span>
            </div>
          </div>

          <div className="flex flex-wrap justify-center gap-6 font-semibold">
            <a href="#mission" className="hover:text-white transition">Mission</a>
            <a href="#pillars" className="hover:text-white transition">Pillars</a>
            <a href="#calculator" className="hover:text-white transition">Calculator</a>
            <a href="#showcase" className="hover:text-white transition">Showcase</a>
            <a href="#blueprints" className="hover:text-white transition">App specs</a>
          </div>

          <div>
            <p className="text-right text-[10px]">&copy; 2026 ValueAfrica Innovation Hub. Abuja Office & Kumasi Sorting Docks. All rights reserved.</p>
          </div>
        </div>
      </footer>

    </div>
  );
}
