# FOREST SERVICE

This application allows Rangers to track wildlife sightings in a game reserve/ gamepark

## Built With

- JAVA 
- J-UNIT5
- GRADLE
- JAVA SPARK



## Getting Started

To get a local copy, follow these simple example steps.

### Prerequisites

A computer with a working and up to date web browser.

### Setup

- Clone the repository to your folder of choice using Git (or just download all the files)
- Install JVM 
- Install Java IDE of your choice

```
$ git clone https://github.com/saretoduncan/Forest-Service.git
$ psql
$ CREATE DATABASE wildlife_tracker;
$\c wildlife_tracker
$ CREATE TABLE animals (id serial PRIMARY KEY, name varchar, danger varchar, health varchar, age varchar, location varchar);
$ CREATE TABLE sighting (id serial PRIMARY KEY, animal id int, location varchar, rangername varchar, lastseen timestamp);

```

## Author

👤 **Author**

By Duncan Kipkosgei Moiyo

- GitHub: [@saretoduncan](https://github.com/saretoduncan)
- twitter: [@duncan_sareto](https://twitter.com/duncan_sareto)
- email: <a href="mailto:duncan.moiyo@student.moringaschool.com"> mail📪</a>

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

Feel free to check the [issues page](./ISSUE_TEMPLATE/feature_request.md).


## Show your support

Give a ⭐️ if you like this project!

## Acknowledgments

- Appreciation to Moringa school for giving me this opportunity to learn 😊

## 📝 License

This project is [MIT](./LICENSE) licensed.
