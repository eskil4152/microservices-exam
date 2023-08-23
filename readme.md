I did not manage to get rabbitmq test containers to work. I tried exposing multiple ports, and looking on 
various sites to no avail. It says it can not bind address to port 5672, or any other ports, resulting in a random port
every time it starts, if it does. 

I did not use much cache, since I did not see where it would help, without caching what is not supposed to be
cached. Only place with cache is itemservice.


To start services or databases from docker compose you can use profiles: 
    "database" for all databases
    "service" for all services (everything not db)
    "rabbit" for rabbit without additional services, and xservice for xservice
    "all" for all

To view all items:
    api/item/all?page=x
To view one item:
    api/item/{id}

Order item:
    api/order/{item id}
View order
    api/order/track/{order id}
View all orders:
    api/order/track/all?page=x