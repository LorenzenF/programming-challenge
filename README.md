# Programming Challenge Solution

This repository contains my solution for the BettercallPaul programming challenge.

## Overview

The application processes two datasets:

- **Weather data**: Finds the day with the smallest temperature spread
- **Country data**: Finds the country with the highest population density

## Features

- Generic CSV reader with reusable mapping logic
- Clear separation between data access, domain models, and business logic
- Input validation and error handling
- Unit tests for all core components

## Architecture

- **CsvDataReader**: Handles CSV reading and validation
- **Domain models**: `WeatherRecord`, `CountryRecord`
- **Services**: `WeatherService`, `CountryService`
- **App**: Entry point orchestrating the workflow

## Focus

- Clean architecture
- Separation of concerns
- Readability and maintainability
- Testability
- Robust error handling

## Tests

All core components are covered by unit tests.

## Original Challenge Description

The original challenge description can be found in: CHALLENGE.asciidoc