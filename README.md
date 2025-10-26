Library Management System :

Tables Involved :
1. library
2. aisle
3. book
4. aisle_book

REST API Endpoints :
POST Request - save library information
http://localhost:8086/api/libraries
{
  "libraryName": "Central Library",
  "aisles": [
    {
      "aisleName": "Natural History",
      "books": [
        {"bookName": "System design intense"},
        {"bookName": "Core Java"}
      ]
    }
  ]
}

GET Requst - retrieving details based on library name && aisle name
http://localhost:8086/api/libraries/books?libraryName=CENTRAL LIBRARY&aisleName=NATURAL HISTORY

GET - get all aisles based on library name
http://localhost:8086/api/libraries/Central Library/aisles

PUT - update library information
http://localhost:8086/api/libraries/1

Swagger UI :

http://localhost:8086/swagger-ui/index.html











