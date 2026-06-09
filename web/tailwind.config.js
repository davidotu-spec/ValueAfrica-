/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        terracotta: {
          light: '#D38A56',
          DEFAULT: '#B55D21', // Primary Rust Accent
          dark: '#8D4311'
        },
        leafTeal: {
          light: '#4B7B34',
          DEFAULT: '#2D4F1E', // Deep Forest Green
          dark: '#1B3012'
        },
        sunOchre: {
          light: '#E5BD8F',
          DEFAULT: '#DDA15E', // Warm Gold Ochre
          dark: '#B97F3B'
        },
        warmCream: {
          light: '#FCFBF9',
          DEFAULT: '#FCF9F5', // Sand Ivory background
          dark: '#F4EBE2'     // Sand Oatmeal surface
        },
        espresso: {
          light: '#432C1D',
          DEFAULT: '#2D241E', // Espresso Dark Coffee Text/Neutral
          dark: '#1E1815'     // Deep Charcoal-coffee background
        }
      },
      fontFamily: {
        sans: ['"Inter"', 'sans-serif'],
        serif: ['"Playfair Display"', 'Georgia', 'serif'],
      }
    },
  },
  plugins: [],
}
