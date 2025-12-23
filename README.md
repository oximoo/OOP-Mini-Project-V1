# Rental Management System (Java OOP Project)

## 📌 Overview
The **Rental Management System** is a Java-based Object-Oriented Programming (OOP) project designed to automate and simplify the management of rental properties such as boarding houses, apartments, and rental homes. This system helps landlords efficiently manage tenants, payments, bills, and inventory using a modular and scalable design.

This repository contains **Version 2 (src_v2)** of the project, which includes improved features and code structure compared to the initial version.

---

## 🎯 Objectives
- Replace manual rental management with an automated system.
- Reduce errors and inefficiencies in tracking payments and tenant details.
- Provide a user-friendly interface for landlords and property managers.

---

## 🛠️ Technologies Used
- **Language:** Java
- **Paradigm:** Object-Oriented Programming (OOP)
- **Core Concepts:** Classes, Objects, Inheritance, Polymorphism, Encapsulation
- **IDE:** IntelliJ IDEA (recommended)

---

## ✅ Features
### **Landlord Module**
- Add and remove properties.
- View financial analytics (income, occupancy).
- Manage inventory for properties.

### **Property Module**
- Manage multiple property types: **Hostel** or **House/Apartment**.
- Add/remove tenants.
- Assign tenants to rooms.
- Check vacancies.
- Manage inventory items.

### **Tenant Module**
- View and pay bills.
- Track payment history.
- Receive notifications for due dates and confirmations.

### **Payment & Billing**
- Record payments (monthly rent, key money).
- Generate and assign bills (electricity, water).
- Send reminders for due dates.

### **Inventory Management**
- Track furniture and appliances.
- Update item conditions.
- Assign items to rooms.

---

## 📂 Project Structure
```
src_v2/
├── Bill.java
├── InventoryItem.java
├── Landlord.java
├── Main.java
├── Payment.java
├── Property.java
├── Room.java
├── Tenant.java
```

---

## 🚀 How to Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/oximoo/OOP-Mini-Project-V1.git
   cd OOP-Mini-Project-V1/src_v2
   ```

2. **Compile the Java files:**
   ```bash
   javac *.java
   ```

3. **Run the main program:**
   ```bash
   java Main
   ```

---

## 📖 How It Works
- **Main.java** acts as the entry point.
- The system uses **Java classes** to represent real-world entities:
  - `Landlord`, `Property`, `Tenant`, `Payment`, `Bill`, `InventoryItem`.
- Each class encapsulates data and methods relevant to its role.
- The program demonstrates OOP principles like **inheritance**, **encapsulation**, and **polymorphism**.

---

## 🔍 Improvements in Version 2
- Enhanced modularity and code readability.
- Better handling of tenant-room assignments.
- Added inventory management features.
- Improved payment and bill tracking.

---

## 📌 Future Enhancements
- GUI interface for better usability.
- Database integration for persistent storage.
- Automated email/SMS notifications.

---

## 👥 Contributors
- W.W.M.G. Dhanundara (EC/2023/025)
- A.G.H.H. Wimalasuriya (EC/2023/002)
- E.I.S. Weerasooriya (EC/2023/022)
- R.A.L.D. Ranasinghe (EC/2023/006)
- K.D.C. Geethanjana (EC/2023/072)

---

## 📜 License
This project is for educational purposes only.
