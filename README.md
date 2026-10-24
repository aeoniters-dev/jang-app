# Jang — app kaunter jualan minuman

- **Page (GitHub Pages):** https://aeoniters-dev.github.io/jang-app/ — HP boss (iPhone): buka di Safari → Share → Add to Home Screen.
- **APK staff (tablet Android):** Releases → `JangKaunter.apk`. Kunci landscape, skrin tak padam. APK = WebView buka page di atas, jadi isi app update sendiri.
- APK baru hanya dibina bila `android/` berubah (GitHub Actions "Bina APK"). Tandatangan guna kunci tetap dari secret `JANG_KEYSTORE_B64` + `JANG_KEYSTORE_PASS`.

## v1.0 (ujian)
Skrin kaunter satu skrin landscape, resit, lapor stok rosak, skrin HP Boss. **Data belum online** (hilang bila app ditutup/refresh). Stok & resepi = CONTOH. Supabase masuk v1.1.
