# Learning Microservices

This repository contains the code and notes from my journey of learning the **Microservices Architecture Pattern** from scratch.

## Step 1: Environment Configuration and Setup

### Prerequisites

Before starting, install **SDKMAN!**, which makes it easy to manage multiple Java versions on your machine.

### Install SDKMAN!

```bash
curl -s "https://get.sdkman.io" | zsh
```

### Load SDKMAN! Environment

```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

### Verify Installation

```bash
sdk version
```

---

## Install Java

This project uses **Java 17 (Temurin distribution)**.

### View Available Java Versions

```bash
sdk list java
```

### Install Java 17

Select the desired version identifier from the list and install it:

```bash
sdk install java 17.0.19-tem
```

### Switch Between Java Versions

SDKMAN! allows you to install and switch between multiple Java versions.

Example:

```bash
sdk use java 21.0.8-tem
```

To verify the active Java version:

```bash
java -version
```

---

## IDE Installation

I recommend using **IntelliJ IDEA** for Java development.

### Install JetBrains Toolbox

Download and install JetBrains Toolbox:

https://www.jetbrains.com/toolbox-app/

### Install IntelliJ IDEA

After installing JetBrains Toolbox:

1. Launch JetBrains Toolbox.
2. Find **IntelliJ IDEA** in the available products.
3. Click **Install**.
4. Open IntelliJ IDEA after the installation completes.

### Installation Screenshot

<img src="images/IntelliJInstallation.png" alt="IntelliJ IDEA Installation" width="50%">

---

## Next Steps

The next section will cover creating the first microservice project and setting up the project structure.
