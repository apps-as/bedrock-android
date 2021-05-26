## Contribution
### Requesting changes
If you found an issue or have a feature request, feel free to start a conversation in the `Discussion` menu or open an Issue.

### Public contribution
1. Fork repo
2. Make changes
3. Open pull request

### Publishing
This library is ready to be shared in an AWS S3 backed Maven repository using the [android-maven-publish](https://github.com/wupdigital/android-maven-publish) plugin. To make this work you need to set up the following Gradle properties in your build environment:
* `maven_access`: AWS credential
* `maven_secret`: AWS credential
* `maven_repo`: URL of the maven repo

_Never include these properties in your commit!_

### Internal maintenance
For local development you may use this repo directly from the internal Maven repository or refer to it using the `mavenLocal` directive.
If you decide to publish the ongoing changes, you must follow these rules:
  1. Bump version according to the magnitude of your changes (semver here)
  2. Add the `snapshot` suffix and ordinal if necessary (eg. `snapshot01`)
  3. You may delete intermediate snapshot releases from the repository if you wish

##### Generally, do the following:
  1. Create a new feature branch, optionally include related ticket number
  2. Make changes
  3. Open pull request and assign it to one of the other contributors

##### Before release
  1. Bump version according to the magnitude of changes (semver here)
  2. Create a tag with the current version number in the form of `v*.*.*`
  3. Create Github release from the tag (use tag as the name)
  4. Run `Publish to maven repo` action
