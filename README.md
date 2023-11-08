# Project FileManager
#### A file sharing platform in Spring Boot and React

## Build instructions

### AWS S3 setup

Currently the default is to use S3 as file storage backend, in order to be able to upload and download files via the file manager you need to allow the "default credentials provider" to know about your AWS key and secret, for example by populating the values in ~/.aws/:

*~/.aws/credentials*

```
[default]
aws_access_key_id=<AWS ACCESS KEY>
aws_secret_access_key=<AWS SECRET KEY>
```

*~/.aws/config*

```
[default]
region=<AWS REGION>
```

### Database setup

Setup your database (MariaDB is supported for now) and export the following environment variables:

```
export FILEMANAGER_DB_USERNAME=<DB username>
export FILEMANAGER_DB_PASSWORD=<DB password>
export FILEMANAGER_DB_HOST=<DB host>
export FILEMANAGER_DB_PORT=<DB port>
```

### Application setup
1. ./mvnw spring-boot:run
2. npm i
3. npm run watch
4. Access over http://localhost:8080/


## TODO
- User accounts and login
- Directories creation
- File/directory removal
- ...

MIT License
