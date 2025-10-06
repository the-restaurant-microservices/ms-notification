# 📬 ms-notification

**Application Name:** `ms-notification`  
**Description:** Notification & Email Microservice

---

## 📘 Ümumi Məlumat

Bu microservice sistem daxilində baş verən hadisələrə əsasən (Kafka event-ləri) istifadəçilərə **email bildirişləri** göndərir.  
Əsas məqsəd — digər servislərdən gələn məlumatları dinləyərək bildiriş sistemini təmin etməkdir.

---

## ⚙️ Əsas Məntiq

### 📨 Kafka Consumer
- **ms-restaurant** servisi yeni restoran yaradanda `restaurant.created` adlı Kafka topic-ə event göndərir.
- `ms-notification` servisi həmin topic-i dinləyir və event gələn kimi **Gmail SMTP** vasitəsilə email göndərir.

### 📧 Mail Service
- Email göndərişi üçün **Spring Boot Mail Starter** istifadə olunur.
- Gmail üçün 2FA aktivləşdirilib və **App Password** vasitəsilə təhlükəsiz autentifikasiya aparılır.
- Emaillərin göndərilməsi `MailService` klasında `sendNotificationEmail()` metodu ilə həyata keçirilir.

---

## 🛠️ İstifadə Olunan Texnologiyalar

| Texnologiya | Məqsəd |
|--------------|--------|
| **Spring Boot 3** | Framework və tətbiq infrastrukturu |
| **Spring Kafka** | Kafka mesajlarını dinləmək və işləmək |
| **Spring Mail** | SMTP ilə email göndərilməsi |
| **Lombok** | Kodun sadələşdirilməsi |
| **Docker** | Kafka və Zookeeper konteynerləri üçün |
| **Gmail App Password** | Təhlükəsiz SMTP autentifikasiyası |

---

## 🔁 İş Axını (Workflow)

1. **Yeni restoran yaradılır (ms-restaurant)**  
   → Kafka event `restaurant.created` göndərilir.

2. **ms-notification** həmin topic-i dinləyir  
   → Event gəlir → payload oxunur → email formalaşdırılır.

3. **Email göndərilir**  
   → Gmail SMTP üzərindən müvafiq ünvana bildiriş göndərilir.

---

## 🧠 Niyə belə dizayn seçilib?

- **Asinxron əlaqə:** Kafka ilə servislər bir-birindən asılı deyil.
- **Email inteqrasiyası:** Sadə və təhlükəsiz şəkildə Gmail SMTP ilə qurulub.
- **Error Handling:** Mail və Kafka üçün ayrıca exception idarəetməsi.
- **Reusability:** MailService gələcəkdə digər event növləri üçün də istifadə oluna bilər.

---

## ✅ Nəticə

`ms-notification` servisi sistemdə baş verən hadisələrə real vaxtda reaksiya verir və email göndərməklə **bildiriş mexanizmini** həyata keçirir.  
Layihə Kafka ilə event-driven yanaşmanı və SMTP ilə praktik email inteqrasiyasını nümayiş etdirir.
