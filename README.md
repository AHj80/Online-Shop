# 🛍️ Online Shop
#
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVVM-orange?style=for-the-badge)

## 📜 Overview

### English

Online Shop is a modern Android e-commerce application developed as a portfolio project using Kotlin and Jetpack Compose.

The project follows the MVVM pattern alongside Clean Architecture principles to create a scalable, maintainable, and organized codebase.

The application allows users to browse products, explore categories, view product details, manage authentication, and experience a modern shopping interface built entirely with Jetpack Compose.

Since this project was developed independently without access to a production backend, MockAPI has been used to simulate server-side data and API responses.

The authentication process was also redesigned and improved to overcome MockAPI limitations; this involved implementing a custom One-Time Password (OTP) verification flow tailored to this portfolio project (using fake notifications).

---

### فارسی

پروژه **Online Shop** یک اپلیکیشن فروشگاهی مدرن برای اندروید است که به عنوان نمونه‌کار و پروژه رزومه با استفاده از زبان Kotlin و Jetpack Compose توسعه یافته است.

این پروژه بر پایه معماری MVVM و اصول Clean Architecture طراحی شده تا ساختاری کاملاً مقیاس‌پذیر، قابل نگهداری و منظم داشته باشد.

کاربران می‌توانند محصولات را مشاهده و جستجو کنند، دسته‌بندی‌ها را بررسی کرده، جزئیات هر محصول را ببینند و فرآیند احراز هویت را در محیطی مدرن که تماماً با Jetpack Compose ساخته شده است، تجربه نمایند.

از آنجا که این پروژه به صورت مستقل توسعه یافته و دسترسی به سرور واقعی وجود نداشته، از **MockAPI** جهت شبیه‌سازی داده‌های سرور و پاسخ‌های API استفاده شده است.

همچنین به دلیل محدودیت‌های MockAPI، فرآیند احراز هویت بازطراحی شد و سیستم تایید رمز یک‌بارمصرف (OTP) به صورت اختصاصی برای این پروژه (با استفاده از اعلان‌های محلی و آفلاین) پیاده‌سازی شده است.

---

# 📱 Screenshots
#

|                                                             صفحه خانه                                                              |                                                              سبد خرید                                                              |                                                            جزئیات محصول                                                            |                                                            صفحه پروفایل                                                            |
|:----------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------:|
| <img width="196" height="462" alt="1" src="https://github.com/AHj80/Online-Shop/releases/download/OnlineShop/1788639532160.jpg" /> | <img width="196" height="462" alt="2" src="https://github.com/AHj80/Online-Shop/releases/download/OnlineShop/1788639532152.jpg" /> | <img width="185" height="439" alt="3" src="https://github.com/AHj80/Online-Shop/releases/download/OnlineShop/1788639532137.jpg" /> | <img width="193" height="450" alt="4" src="https://github.com/AHj80/Online-Shop/releases/download/OnlineShop/1788639532145.jpg" /> | 

Download Apk

[![Download APK](https://img.shields.io/badge/Download-APK%20Release-brightgreen?style=for-the-badge&logo=android)](https://github.com/AHj80/Online-Shop/releases/download/OnlineShop/app-release.apk)

---

# 🚀 Features

### English

- User Authentication
- Custom OTP Verification Flow
- Product Listing
- Product Details
- Categories
- Search
- Modern UI with Jetpack Compose
- Material 3 Design
- State Management using StateFlow
- Offline Data Support
- Responsive UI
- Clean Architecture
- MVVM Architecture

---

### فارسی

- احراز هویت کاربران (Authentication)
- فرآیند اختصاصی تایید رمز یک‌بارمصرف (OTP Flow)
- نمایش لیست محصولات
- نمایش جزئیات کامل محصول
- دسته‌بندی محصولات
- جستجوی پیشرفته در محصولات
- رابط کاربری مدرن با Jetpack Compose
- طراحی استاندارد بر پایه Material 3
- مدیریت وضعیت (State Management) با StateFlow
- پشتیبانی از ذخیره‌سازی و داده‌های آفلاین
- رابط کاربری واکنش‌گرا (Responsive UI)
- پیاده‌سازی بر پایه Clean Architecture
- الگوی معماری MVVM

---

# 🛠 Tech Stack

### English

- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Clean Architecture
- Hilt
- Retrofit
- Kotlinx Serialization
- Coroutines
- StateFlow
- Navigation Component
- DataStore
- Coil
- MockAPI

---

# 🏛 Architecture

### English

The project follows Clean Architecture using the MVVM pattern:

```text
Presentation ──► Domain ──► Data
```

This structure helps separate business logic from the UI and improves maintainability and scalability.

---

### فارسی

این پروژه بر اساس اصول **Clean Architecture** و الگوی **MVVM** پیاده‌سازی شده است:

```text
Presentation ──► Domain ──► Data
```

این تفکیک لایه‌ای باعث جداسازی کامل منطق برنامه (Business Logic) از لایه رابط کاربری (UI) شده و تست‌پذیری و توسعه‌پذیری پروژه را به حداکثر می‌رساند.

---

# 📂 Project Structure

### English

```text
feature/
├── presentation/
├── domain/
└── data/
```

---

### فارسی

ساختار پروژه به صورت **Feature-based** (ویژگی‌محور) طراحی شده است؛ به این صورت که هر ویژگی به صورت مستقل شامل ۳ لایه اختصاصی زیر می‌باشد:

```text
feature/
├── presentation/  # Composables, ViewModels & UI States & ScreenStatus
├── domain/        # Use Cases, Domain Models & Repository Interfaces
└── data/          # DTOs, API Services & Repository Implementations
```

---



# 📄 License

This project is created for portfolio purposes.

---

### فارسی

این پروژه صرفاً با هدف ارائه‌ به عنوان نمونه‌کار (Portfolio) توسعه داده شده است.

---



# 👨‍💻 Contact

- **GitHub:** [AHj80](https://github.com/AHj80)
- **Telegram ID:** [@AHj80]
- **Email:** amirhosseinjalili00@gmail.com
