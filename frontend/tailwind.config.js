/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        ice: {
          50: '#F0F9FF',
          100: '#EFF8FF',
          150: '#EAF6FF',
          200: '#DCEFFF',
          300: '#BDDEFF',
        },
        navy: {
          DEFAULT: '#123B63',
          dark: '#0B2540',
          light: '#1E4E7A',
        },
        primary: {
          DEFAULT: '#2563EB',
          dark: '#1D4ED8',
          light: '#3B82F6',
        },
        app: {
          bg: '#EFF8FF',
          card: '#FFFFFF',
          text: '#172033',
          muted: '#64748B',
          success: '#16A34A',
          warning: '#F59E0B',
          error: '#DC2626',
        }
      },
      fontFamily: {
        sans: ['Inter', 'system-ui', '-apple-system', 'sans-serif'],
      },
      boxShadow: {
        'card': '0 2px 8px -1px rgba(18, 59, 99, 0.06), 0 1px 3px -1px rgba(18, 59, 99, 0.04)',
        'card-hover': '0 8px 20px -2px rgba(18, 59, 99, 0.1), 0 4px 6px -2px rgba(18, 59, 99, 0.05)',
      }
    },
  },
  plugins: [],
}
