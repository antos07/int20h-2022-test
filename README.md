# int20h-2023-test

## Where to test?

[Here](https://antos07.pythonanywhere.com/) or locally using docker

## How to run locally using docker-compose?

1. Clone the repo
   Create .env file, which should contain the following lines:

   MYSQL_ROOT_PASSWORD=< insert any password here >

   MYSQL_DJANGO_PASSWORD=< insert any password here >

2. Deploy the project using docker compose: `docker compose up`
3. Open terminal at the website service
4. Run database migrations: `python3 manage.py migrate`
5. Fetch data from TheMealDB.com: `python3 manage.py syncwtihthemealdb`
6. Enjoy :)

## Used meal database

[TheMealDB](https://themealdb.com)

## Where main site is running?

[Pythonanywhere](https://pythonanywhere.com/)
