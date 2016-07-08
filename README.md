# vigilant-sniffle

Continuous delivery with:

* Jenkins
* Gradle
* Artifactory


## Usage

Use `docker-compose`:

```sh
$ docker-compose up -d
$ docker-compose down -v
```


## Endpoints

* Jenkins is at `http://localhost:8080`
* Artifactory is at `http://localhost:8081`

To Jenkins, Artifactory is available at `http://artifactory:8081`. See compose file.
