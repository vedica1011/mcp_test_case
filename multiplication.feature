Feature: MCP Multiplication Service
    As a user of the MCP service
    I want to multiply two numbers
    So that I can get their product

    Background:
        Given the MCP server is running at "http://127.0.0.1:8087"

    Scenario: Successful multiplication of two positive numbers
        When I send a multiplication request with a=10 and b=8
        Then I should receive a successful response
        And the result should be 80

    Scenario Outline: Multiplication with different number combinations
        When I send a multiplication request with a=<first_number> and b=<second_number>
        Then I should receive a successful response
        And the result should be <expected_result>

        Examples:
            | first_number | second_number | expected_result |
            | 5           | 5             | 25              |
            | 0           | 10            | 0               |
            | -5          | 3             | -15             |
            | -4          | -4            | 16              |
            | 1000        | 0             | 0               |

    Scenario: Server availability check
        When I send a request to the home endpoint
        Then I should receive a successful response
        And the message should be "welcome to the demo MCP"

    Scenario: Error handling for invalid input types
        When I send a multiplication request with invalid input types
        Then I should receive an error response

    Scenario: Error handling for missing parameters
        When I send a multiplication request without parameters
        Then I should receive an error response
