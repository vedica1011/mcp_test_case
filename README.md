# MCP Multiplication Service

This project demonstrates a simple Model Context Protocol (MCP) implementation with a multiplication service. It includes both the server and client components, along with comprehensive BDD tests.

## Project Structure

- `server_mcp_test.py`: FastAPI server implementing the MCP multiplication service
- `client_mcp_test.py`: Python client for testing the multiplication service
- `multiplication.feature`: Gherkin feature file describing test scenarios
- Java-based test automation framework using Cucumber

## Setup and Running

### Server
```bash
python server_mcp_test.py
```

### Client
```bash
python client_mcp_test.py
```

### Running Tests
```bash
mvn clean test
```

## Test Scenarios

The test suite covers:
- Basic multiplication functionality
- Different number combinations
- Server availability
- Error handling for invalid inputs
- Error handling for missing parameters

## Dependencies

### Python
- FastAPI
- FastMCP
- Uvicorn
- Requests

### Java Testing
- Cucumber
- REST Assured
- JUnit
- JSON library
