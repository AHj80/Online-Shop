# 🛍️ Online Shop

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

More screenshots will be added soon.
| صفحه ورود | دسته بندی ها | جزئیات محصول | صفحه پروفایل |  اجرای برنامه(دمو) |
| :---: | :---: | :---: | :---: | :---: |
| <img width="196" height="462" alt="1" src="https://github.com/user-attachments/assets/1b4a8a86-70fd-488e-a0ae-cdaeb83367f1" /> | <img width="196" height="462" alt="2" src="https://github.com/user-attachments/assets/7259ee68-eaae-4052-a657-6483f4549148" />| <img width="185" height="439" alt="3" src="https://github.com/user-attachments/assets/dd86b54c-a444-4bfb-a4bf-3a2ca2f8100c" /> | <img width="193" height="450" alt="4" src="https://github.com/user-attachments/assets/1154d5c1-6b8f-4e3f-87aa-51f81a5ece3a" />| https://github.com/user-attachments/assets/388c768a-3f7c-4540-822f-dad746f36020|


اسکرین‌شات‌های بیشتر پس از تکمیل نهایی رابط کاربری در این بخش قرار خواهند گرفت.

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

# 🚧 Current Status

### English

This project is currently under active development. Most core functionalities have already been implemented.

The remaining work mainly includes:
- Final code refactoring
- Completing the remaining features
- Improving animations and transitions
- Additional UI polishing
- Performance optimization
- General code cleanup

The goal is to continuously improve the project while applying modern Android development best practices.

---

### فارسی

این پروژه در حال حاضر در **مرحله توسعه فعال** قرار دارد و اکثر قابلیت‌های اصلی آن پیاده‌سازی شده‌اند.

اقدامات در حال انجام و برنامه‌های آتی:
- بازبینی و بازنویسی نهایی کدها (Refactoring)
- تکمیل قابلیت‌های باقی‌مانده
- بهبود انیمیشن‌ها و جابه‌جایی بین صفحات (Transitions)
- ارتقای جزئیات و زیبایی‌شناسی رابط کاربری
- بهینه‌سازی عملکرد (Performance Optimization)
- تمیزکاری کلی و رعایت دقیق‌تر تمایز لایه‌ها

هدف، ارتقای مداوم پروژه با به‌کارگیری آخرین استانداردهای روز توسعه اندروید است.

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
