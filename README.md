<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/willystw/tabunganku">
    <img src="docs/images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Tabunganku</h3>

  <p align="center">
    Backend of Tabunganku: an application to track your spending.
    <br />
    <a href="https://github.com/willystw/tabunganku"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/willystw/tabunganku">View Demo</a>
    ·
    <a href="https://github.com/willystw/tabunganku/issues">Report Bug</a>
    ·
    <a href="https://github.com/willystw/tabunganku/issues">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
This is the backend component of Tabunganku, an application to track spending. For the backend implementation, refer to [here][tabunganku-fe-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With
* [![Spring][spring.io]][spring-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* JDK 17
* Maven
* Postgres

### Installation

#### Postgres Preparation
_For first time use_
1. Start Postgres
2. Run the script in `src/main/resources/initial_table.sql`
#### Starting the Service

1. Clone the repo
   ```sh
   git clone https://github.com/willystw/tabunganku.git
   ```
2. Create `.env` in the main directory and replace the value below with relevant information
   ```
    #Database URL
    JDBC_DB_URL=jdbc:postgresql://example.com
    
    #Database Username
    JDBC_DB_USERNAME=foo
    
    #Database Password
    JDBC_DB_PASSWORD=bar
    
    #Defining cross origins policy
    cross.origins=localhost
   ```
3. Build the project
   ```sh
   mvn clean install -Dpg.url=<postgres url> -Dpg.username=<postgres username> -Dpg.password=<postgres password>
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
### Add New User
Creating a user data is the prerequisite to start Tabunganku FE. To do this, hit `/users/add` with the payload
```json 
{
    "username": "<username>",
    "email": "<email>"
}
```

We can use CURL to achieve this (`localhost:8080` is the default endpoint for Spring Boot application)
```sh
curl --location 'http://localhost:8080/users/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "<username>",
    "email": "<email>"
}'
```
### API Documentation
To view API documentation, start the backend service and go to `/tabunganku-documentation`


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap
See the [open issues](https://github.com/willystw/tabunganku/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Your Name - fwillysetiawan@gmail.com

Project Link: [https://github.com/willystw/tabunganku](https://github.com/willystw/tabunganku)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* [JOOQ Library](https://www.jooq.org/)
* [Springdoc](https://springdoc.org/)
* [README Template](https://github.com/othneildrew/Best-README-Template)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/willystw/tabunganku.svg?style=for-the-badge
[contributors-url]: https://github.com/willystw/tabunganku/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/willystw/tabunganku.svg?style=for-the-badge
[forks-url]: https://github.com/willystw/tabunganku/network/members
[stars-shield]: https://img.shields.io/github/stars/willystw/tabunganku.svg?style=for-the-badge
[stars-url]: https://github.com/willystw/tabunganku/stargazers
[issues-shield]: https://img.shields.io/github/issues/willystw/tabunganku.svg?style=for-the-badge
[issues-url]: https://github.com/willystw/tabunganku/issues
[license-shield]: https://img.shields.io/github/license/willystw/tabunganku.svg?style=for-the-badge
[license-url]: https://github.com/willystw/tabunganku/blob/master/LICENSE
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/willy-s-0147561a6
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
[spring.io]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[spring-url]: https://spring.io/
[tabunganku-fe-url]: https://github.com/willystw/tabunganku-fe