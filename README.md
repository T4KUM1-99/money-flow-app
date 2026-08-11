# Money Flow App

Aplikasi Android sederhana untuk membantu mengelola dan memantau alur keuangan pribadi.

## 📱 Tentang Project

**Money Flow App** adalah project aplikasi Android yang dibangun menggunakan **Kotlin** dan **Jetpack Compose**. Project ini menggunakan Android Gradle Plugin dengan konfigurasi modern berbasis Kotlin DSL.

Repository:
- GitHub: https://github.com/T4KUM1-99/money-flow-app

## ✨ Teknologi

Project ini menggunakan:

- **Kotlin**
- **Android SDK**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **AndroidX Lifecycle & ViewModel**
- **Gradle Kotlin DSL**
- **Java 11**

## 📋 Persyaratan

Sebelum menjalankan project, pastikan sudah tersedia:

- Android Studio versi terbaru yang mendukung project Android berbasis Kotlin dan Jetpack Compose
- JDK 11 atau kompatibel
- Android SDK dengan API Level 35
- Android device atau emulator dengan Android 8.0 (API 26) atau lebih baru

## 🚀 Cara Menjalankan

### 1. Clone repository

```bash
git clone https://github.com/T4KUM1-99/money-flow-app.git
```

### 2. Masuk ke folder project

```bash
cd money-flow-app
```

### 3. Buka dengan Android Studio

Buka folder `money-flow-app` melalui Android Studio, lalu tunggu proses **Gradle Sync** selesai.

### 4. Jalankan aplikasi

Pilih emulator atau perangkat Android yang tersedia, kemudian klik:

**Run ▶**

Atau gunakan Gradle Wrapper dari terminal:

#### Windows

```bash
gradlew.bat assembleDebug
```

#### Linux / macOS

```bash
./gradlew assembleDebug
```

## 🏗️ Konfigurasi Android

| Konfigurasi | Nilai |
|---|---|
| Namespace | `com.moneyflow.app` |
| Application ID | `com.moneyflow.app` |
| Compile SDK | 35 |
| Target SDK | 35 |
| Minimum SDK | 26 |
| Version Code | 1 |
| Version Name | 1.0 |
| Java | 11 |
| Kotlin JVM Target | 11 |
| UI Toolkit | Jetpack Compose |

## 📂 Struktur Project

```text
money-flow-app/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/moneyflow/app/
│   │       ├── res/
│   │       │   └── values/
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

## 🛠️ Build

Untuk membuat APK debug:

```bash
./gradlew assembleDebug
```

Pada Windows:

```bash
gradlew.bat assembleDebug
```

Hasil build debug dapat ditemukan di:

```text
app/build/outputs/apk/debug/
```

## 🔮 Pengembangan Selanjutnya

Beberapa pengembangan yang dapat ditambahkan:

- [ ] Pencatatan pemasukan dan pengeluaran
- [ ] Kategori transaksi
- [ ] Ringkasan kondisi keuangan
- [ ] Riwayat transaksi
- [ ] Filter transaksi berdasarkan tanggal
- [ ] Grafik pemasukan dan pengeluaran
- [ ] Penyimpanan data lokal
- [ ] Backup dan restore data
- [ ] Dark mode
- [ ] Export data

## 🤝 Contributing

Pull request dan kontribusi sangat terbuka.

1. Fork repository
2. Buat branch baru

```bash
git checkout -b feature/nama-fitur
```

3. Commit perubahan

```bash
git add .
git commit -m "feat: tambah nama fitur"
```

4. Push branch

```bash
git push origin feature/nama-fitur
```

5. Buat Pull Request

## 📄 License

Lisensi project belum ditentukan.

---

Made with ❤️ using Kotlin & Jetpack Compose.
