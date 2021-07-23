#Code Challenge-Juan Reyes Leos  
The code challenge was created to measure the skills desired by the CLIP company
#### Requirements
For run this Application in  your computer you need  installing the following:

    1. Java 1.8
    2. Maven 3.6
    3. Git 2.21

#### Clone Project   

For clone the project in your local machine you should execute the following command 

```sh
git clone https://github.com/juanreyesleos/code-challenge.git
```

###Run the application

>**Step 1** In the folder where you cloned the project, you need to go to the code challenge folder with the following command.

```sh
cd code-challenge
```

>**Step 2** Run the following command for download the dependencies and build jar file 

```sh
mvn clean package spring-boot:repackage
```

>**Step 2** Execute jar file 

```sh
java -jar target/code-challenge-0.0.1-SNAPSHOT.jar
```

#Operations

● Make/Accept a transaction.

```sh
$ curl -X POST \
  http://localhost:8080/transaction \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
"amount": 30,
"clipUser": "carlos",
"carddata": "1294730573049528"
}'
```

If the response is correct you will see something like the following JSON.

```sh
	{
    "id": 1,
    "amount": 30,
    "clipUser": "carlos",
    "carddata": "1294730573049528",
    "date": "2021-07-21T02:37:05.984+00:00",
    "paid": false,
    "disbursement": null
    }
```


● Get transactions by clip_user.

```sh
$ curl -X GET \
  http://localhost:8080/transaction/juan\
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
"amount": 150,
"clipUser": "juan",
"carddata": "111222233334444"
}'
```

If everything is ok, this endpoint will return a JSON like the following

```sh
[
    {
        "id": 1,
        "amount": 30,
        "clipUser": "carlos",
        "carddata": "1294730573049528",
        "date": "2021-07-19T05:27:58.815+00:00",
        "paid": true,
        "disbursement": {
            "id": 39,
            "totalamount": 30,
            "clipUser": "carlos",
            "date": "2021-07-20T03:15:30.460+00:00"
        }
    },
    {
        "id": 2,
        "amount": 56,
        "clipUser": "carlos",
        "carddata": "1294730573049528",
        "date": "2021-07-21T02:37:05.984+00:00",
        "paid": false,
        "disbursement": null
    }
]
```

● Make a disbursement.




```sh
$ curl -X POST \
  http://localhost:8080/disbursement \
  -H 'cache-control: no-cache' \
```
If everything is ok, this endpoint will return a JSON like the following.

```sh
[
    {
        "id": 1,
        "totalamount": 300,
        "clipUser": "carlos",
        "date": "2021-07-21T02:48:21.787+00:00"
    },
    {
        "id": 2,
        "totalamount": 2775,
        "clipUser": "juan",
        "date": "2021-07-21T02:48:21.841+00:00"
    }
]

```

● Get disbursements

```sh
$ curl -X GET \
  http://localhost:8080/disbursement \
  -H 'cache-control: no-cache' \
```

If everything is ok, this endpoint will return a JSON like the following

```sh
[
    "disbursement 1: 30.0 pesos - carlos - date: 19/07/21",
    "disbursement 2: 30.0 pesos - carlos - date: 20/07/21",
    "disbursement 3: 120.0 pesos - juan - date: 19/07/21",
    "disbursement 4: 2775.0 pesos - juan - date: 20/07/21"
]
```

#Data Base
This application uses the H2 database, it is stored in memory.

The database has data preloaded if you want to verify this data, you can access it through the following link and your Internet browser.


	http://localhost:8080/h2-console/login.jsp









