# ValueAfrica Innovation Hub Landing Page

This directory contains a **high-fidelity, responsive React landing page** for ValueAfrica, styled with **Tailwind CSS**. 

It is designed to serve as the modern face of the business, aligning perfectly with the custom theme palettes (Terracotta rust, LeafTeal green, and Warm Cream tones) and features found within the native Android application dashboard.

---

## 🌐 FAQ: Can I publish this as a website?

**Absolutely!** While the native Android application is built in Kotlin and runs as an app on your phone, this responsive React + Tailwind CSS landing page is a fully-compliant web application. It is ready representing the professional face of **ValueAfrica Innovation Hub** online.

You can publish this React page to the public web in under 5 minutes using any modern static hosting service or standard web host (e.g., Netlify, Vercel, Hostinger, GoDaddy, GitHub Pages).

---

## 🛠️ Features Implemented

1. **Strategic Hero Section**: High-impact headlines centering on routing African creators directly to the global marketplace.
2. **Company Mission & Vision**: Directly reflecting ValueAfrica's core vows (local brand packaging, global visibility, trust validation).
3. **Infrastructure Pillars**: Dynamic bento-grid details explaining Abuja/Kumasi consolidated warehousing, CAC & guild peer verification, and global Diaspora sub-channels.
4. **Interactive Financial Casflow Calculator**: Responsive sliders mimicking the mobile app's financial simulation. Adjust monthly shipments, basket sizing, and commission rates to instantly see gross trade volumes and **family livelihoods sustained** in West Africa.
5. **Authentic Provenance Showcases**: An interactive craft carousel profiling premium handwoven Kente, ancient lost-wax Benin bronze figures, organic wellness shea butter, and Aso Oke trench designs.
6. **Mobile App Blueprint Specifications**: Responsive tabs highlighting detailed features for both the **Customer Mobile App** and the **Vendor Mobile App**.
7. **AI Executive Summary Report**: Live preview console demonstrating how Google Gemini summarizes regional sales trends and Customer location hotspots.

---

## 🚀 How to Run Locally

To launch this landing page on your local computer, make sure you have [Node.js](https://nodejs.org) installed, and run:

1. **Navigate into the directory**:
   ```bash
   cd web
   ```

2. **Install all dependencies**:
   ```bash
   npm install
   ```

3. **Run the local development server**:
   ```bash
   npm run dev
   ```
   *Your terminal will display a local link (typically `http://localhost:5173`). Open this URL in your web browser to view your landing page!*

---

## 📦 How to Export/Build for Production

When you are ready to prepare your website for live publication, compile a highly-optimized, fast-loading, single bundle package:

1. **Compile the build**:
   ```bash
   npm run build
   ```

2. **Retrieve your package**:
   In less than a few seconds, Vite will output a production-grade folder named `dist` (located at `web/dist/`).
   This `dist` directory is highly optimized and contains:
   - `index.html`: The entrance document.
   - `/assets`: Concatenated, compressed CSS script bundles and minified React code.

---

## 🌍 How to Deploy to a Web Host

Depending on your hosting arrangements, choose one of these simple publication pathways:

### Option A: Standard Shared Web Hosts (GoDaddy, Hostinger, Bluehost, Namecheap)
If you already own a domain and standard cPanel hosting:
1. Compile the website using `npm run build`.
2. Locate the output `/web/dist/` folder on your computer.
3. Compress the *contents* of the `/web/dist/` directory into a `.zip` file.
4. Log into your web host's **cPanel File Manager**.
5. Navigate to your primary web folder (usually `/public_html`).
6. Select **Upload** and submit your `.zip` file.
7. **Extract** the `.zip` file inside `/public_html`. Your website is now live!

### Option B: High-Performance Serverless Platforms (Netlify, Vercel) - *Highly Recommended & Free!*
These platforms connect to your Git repositories and automatically deploy updates when you push code:
1. Push your repository code to GitHub, GitLab, or Bitbucket.
2. Create an account on [Vercel](https://vercel.com/) or [Netlify](https://www.netlify.com/).
3. Connect your account to your GitHub platform.
4. Select your repository and configure these project settings:
   - **Framework Preset**: `Vite` or `Other`
   - **Root Directory**: `web`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist`
5. Click **Deploy**. Your site will build in seconds and generate a custom, free SSL secured link!

### Option C: GitHub Pages - *Free Hosting from GitHub!*
1. Install the deployment helper tool:
   ```bash
   npm install --save-dev gh-pages
   ```
2. Add these scripts inside your `package.json`:
   ```json
   "predeploy": "npm run build",
   "deploy": "gh-pages -d dist"
   ```
3. Add a `homepage` configuration field:
   ```json
   "homepage": "https://username.github.io/repository-name"
   ```
4. Run:
   ```bash
   npm run deploy
   ```
   *GitHub will automatically create a deployment branch and host your site!*
