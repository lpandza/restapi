# API Application

## Overview
This application provides an API service with an embedded H2 database that is automatically created and populated at startup.

## Getting Started

### Running the Application
1. Start the application using your preferred method (e.g., via IDE or command line)
2. The application will automatically:
   - Create an embedded H2 database
   - Populate the database with initial data
   - Start the H2 console interface
   - Launch the API service

### Access Information
- **API Endpoint**: `http://localhost:8080/api/v1`
- H2 Database Console: The embedded database can be accessed through the H2 console if configured

## Technical Details
- **Port**: 8080
- **Database**: H2 (embedded)
- **API Base Path**: `/api/v1`

## Additional Notes
- No manual database setup is required
- The database is created in-memory and will be reset each time the application restarts
- All necessary tables and initial data are automatically created during startup
