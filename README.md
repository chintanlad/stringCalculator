#  String Calculator TDD - Kata

A clean and extensible **String Calculator** built using **Test-Driven Development (TDD)** in Java.  
Supports addition of both **integers** and **decimal numbers**, with robust delimiter handling, validation, and tracking.

## 📦 Technologies

- Java 17+
- JUnit 5
- Maven (optional for build/testing)
- Follows **TDD** best practices

## ✨ NEW: Decimal Addition Support

The project now includes a separate class to handle **floating-point (double)** numbers in the same flexible way as integers.




## 🚀 Features

- ✅ Add 0, 1, or more numbers
- ✅ **Supports Integer & Decimal Addition**
- ✅ Comma `,` and newline `\n` as default delimiters
- ✅ Custom delimiters:  
  `//[delimiter]\n[numbers]` → `//;\n1;2`
- ✅ Delimiters of any length:  
  `//[***]\n1***2***3`
- ✅ Multiple delimiters supported:  
  `//[*][%]\n1*2%3`
- ✅ Multiple delimiters with length > 1:  
  `//[**][%%]\n1**2%%3`
- ✅ Ignores numbers > 1000
- ✅ Throws exception on **negative numbers**
- ✅ Tracks how many times `add()` was called

### ✅ Class: `DecimalStringCalculator`

Supports:

- Comma, newline, and custom delimiters
- Multi-character and multiple delimiters
- Negative decimal number validation
- Ignores numbers > 1000

## 📁 Project Structure

```bash
string-calculator/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── incubyte/
│   │               └── stringcalculator/
│   │                   ├── StringCalculator.java
│   │                   ├── DecimalStringCalculator.java
│   │                   └── NegativeNumberNotAllowedException.java
│   └── test/
│       └── java/
│           └── com/
│               └── incubyte/
│                   └── stringcalculator/
│                       ├── StringCalculatorTest.java
│                       └── DecimalStringCalculatorTest.java
├── pom.xml
└── README.md
```
## 💻 How to Clone & Run the Project

### 🔹 Clone the Repository

```bash
git clone https://github.com/chintanlad/stringCalculator.git
cd string-calculator
```
