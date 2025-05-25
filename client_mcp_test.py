import requests

def test_multiply(a,b):
    response = requests.post("http://127.0.0.1:8087/dev/mcp/multiple",json={"a":a, "b":b})
    if response.status_code==200:
        print("multiplication mcp called sucessully",response.json()['result'])
    else:
        print("There was an issue..")


if __name__ =="__main__":
    test_multiply(a=10,b=8)

