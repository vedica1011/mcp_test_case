from fastmcp import FastMCP
from fastapi import FastAPI, Body
import uvicorn

app = FastAPI()
mcp = FastMCP("demo server or test")

@mcp.tool()
def multiply(a,b): # understand this function is a tool
    return a*b

@app.post("/dev/mcp/multiple")
def call_multiply(data :dict = Body(...)):  # this is goin to call the tool here ... dot means it is expecting some input
    return {"result":multiply(data.get("a",0),data.get("b",0))}


@app.get("/")
def home():
    return {"message":"welcome to the demo MCP"}


if __name__ =="__main__":
    uvicorn.run(app,host="127.0.0.1",port=8087)