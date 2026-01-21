# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a sample Playwright Java testing project demonstrating browser automation and E2E testing. The project uses Microsoft Playwright for Java with JUnit 5 for test execution and AssertJ for assertions.

**Tech Stack:**
- Java 17
- Playwright 1.57.0
- JUnit Jupiter 6.0.1
- AssertJ 3.27.6
- Maven (build tool)

## Build & Test Commands

### Maven Commands

```bash
# Compile the project
mvn compile

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=ASimplePlaywrightTest

# Run a specific test method
mvn test -Dtest=ASimplePlaywrightTest#shouldShowThePageTitle

# Clean and rebuild
mvn clean install
```

### Running Tests Directly

The project contains two versions of the same test:
- `src/test/java/cz/ivosahlik/playwright/ASimplePlaywrightTest.java` - JUnit test (use `mvn test`)
- `src/main/java/cz/ivosahlik/playwright/ASimplePlaywrightTest.java` - Standalone executable with main method (use `mvn compile exec:java -Dexec.mainClass="cz.ivosahlik.playwright.ASimplePlaywrightTest"`)

## Project Architecture

### Source Structure

The project follows standard Maven conventions:
- `src/main/java` - Main application code (contains standalone test runner)
- `src/test/java` - JUnit test classes
- Package: `cz.ivosahlik.playwright`

### Test Pattern

Both test implementations demonstrate the same Playwright workflow:
1. **Browser Setup** - Launches Chromium in non-headless mode with slow motion (200ms)
2. **Context Configuration** - Creates browser context with 1440x900 viewport
3. **Page Navigation** - Navigates to https://practicesoftwaretesting.com
4. **User Interaction** - Performs search operation for "Bolt"
5. **Assertions** - Validates search results and page title
6. **Cleanup** - Closes browser and Playwright instance

### Playwright Configuration

Default browser launch options used throughout tests:
```java
new BrowserType.LaunchOptions()
    .setHeadless(false)  // Visual mode for debugging
    .setSlowMo(200)      // 200ms delay between operations
```

Viewport is set to 1440x900 for consistent test execution.

## Maintenance

### Updating Playwright Version

Use the provided script to update Playwright across all sample-code branches:
```bash
./scripts/update-playwright.sh
```

This script:
- Fetches all remote branches starting with `sample-code/`
- Updates Playwright to latest version in pom.xml
- Commits and pushes changes to each branch

Alternatively, update manually in pom.xml:
```bash
mvn versions:use-latest-versions -Dincludes=com.microsoft.playwright:playwright
```
