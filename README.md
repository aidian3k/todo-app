# Metodyki wytwarzania oprogramowania - projekt

## Opis aplikacji, która była wykorzystana do testowania
W celu wykonania testów w Selenium potrzebowałem prostej aplikacji, która umożliwia operacje odczytu, zapisu, usunięcia oraz
odświeżenia danych. W tym celu napisałem aplikację do dodawania zadań - aplikacja umożliwia utworzenie użytkownika, zalogowanie się 
oraz wyświetlanie, usuwanie, dodawania oraz odświeżanie zadań użytkownika. Aplikacja została napisana w Angular 14 - do tego użyłem 
również Angular material oraz Tailwind do stylowania. Wygląd aplikacji prezentuje się następująco:
![Screenshot 2023-12-03 at 17 19 19](https://github.com/aidian3k/todo-app/assets/93425971/71859a73-d8be-4aef-a92d-f81a86eb7515)
![Screenshot 2023-12-03 at 17 19 41](https://github.com/aidian3k/todo-app/assets/93425971/57f3ac27-fc4b-445f-97b4-609bb5c70d3c)
![Screenshot 2023-12-03 at 17 20 00](https://github.com/aidian3k/todo-app/assets/93425971/10953b03-17c9-457e-9773-e1104fb80577)

## Testowanie aplikacji w Selenium
Kolejnym krokiem w zadaniu było napisanie prostych testów najważniejszych operacji aplikacji w Selenium. Do wykonania tego zadania
wybrałem Javę - na początku musiałem pobrać odpowiednie paczki z Selenium do Javy oraz skonfigurować driver w taki sposób, żeby 
nie uruchamiał przeglądarki przy testach, tylko robił to headless - przydatne paczki oraz konfiguracje w testach można przedstawić 
następująco:

![Screenshot 2023-12-03 at 17 27 10](https://github.com/aidian3k/todo-app/assets/93425971/353bf864-1f65-43ee-a2be-5dd1390f333c)
![Screenshot 2023-12-03 at 17 27 30](https://github.com/aidian3k/todo-app/assets/93425971/bacd3ddf-2fd8-4884-9a2f-1aa997d62b66)

Następnym krokiem było napisanie przykładowych testów automatycznych po skonfigurowaniu środowiska. W tym celu na początku trzeba 
wybrać odpowiednie elementy na stronie za pomocą xPath, a następnie przejść do napisania przykładowego testu. Przykładowo znajdowanie 
elementów na stronie możemy napisać następująco:
![Screenshot 2023-12-03 at 17 33 02](https://github.com/aidian3k/todo-app/assets/93425971/c2de97f2-ef30-4216-9f07-c9f977d17885)
![Screenshot 2023-12-03 at 17 33 15](https://github.com/aidian3k/todo-app/assets/93425971/c295c728-d636-46b9-b41f-c7441804b012)

Podzieliłem wszystkie funkcjonalności, które testowałem na elementy do weryfikacji, w których są na przykład napisy błędów oraz 
elementy interakcji, czyli na przykład przycisk zaloguj. Następie tak prezentuje się przykładowy test:

![Screenshot 2023-12-03 at 17 34 52](https://github.com/aidian3k/todo-app/assets/93425971/c88d5fee-26a1-4f35-817b-1fa4ccb6fb82)

Test sprawdza czy w przypadku jak nie wpiszemy emaila oraz hasła przy logowaniu wyświetli się odpowiedni komunikat błędu. Możemy 
dodatkowo zauważyć, że po każdym teście zamykamy aktualny driver oraz przy każdym teście tworzymy nowy driver oraz klasy z elementami 
do interakcji oraz sprawdzenia. Inny przykładowy test, na przykład do dodawania zadania prezentuje się następująco:
![Screenshot 2023-12-03 at 17 37 52](https://github.com/aidian3k/todo-app/assets/93425971/fc67aa09-e20b-42d5-9dce-ed1253da1599)

Następnie za pomocą komendy './gradlew test' możemy uruchomić wszystkie testy:
![Screenshot 2023-12-03 at 17 39 27](https://github.com/aidian3k/todo-app/assets/93425971/8069325a-d6e3-4b81-b0c1-6c88835b34eb)

W tym przypadku przeszło 9 testów. Jeden test był failed, ponieważ będzie on wykorzystywany w następnym ćwiczeniu.

## Konfiguracja AzureDevops oraz Github
Kolejnym zadaniem było napisanie pipeline w github actions, który w przypadku gdy nie przechodzą testy automatyczne w selenium,
wstawia zadanie z bugiem na platformę azure-devops. W celu wykonania tego zadania na początku musiałem założyć projekt na platformie
Azdo oraz wygenerować sobie Personal access token, który będzie wykorzystywany w github:
![Screenshot 2023-12-03 at 17 49 30](https://github.com/aidian3k/todo-app/assets/93425971/d855d3d0-1531-40db-9e83-b83a7bfe51f0)
![Screenshot 2023-12-03 at 14 55 19](https://github.com/aidian3k/todo-app/assets/93425971/5c94e8bc-b924-4ade-90c8-3faaab3b287b)

Kolejnym krokiem było wygenerowanie sobie personalnego klucza na github, który jest potrzebny do uzyskiwania informacji z pipeline:
![Screenshot 2023-12-03 at 17 53 47](https://github.com/aidian3k/todo-app/assets/93425971/ff734fe0-41ca-4d52-8cbc-60dac4924144)

Następnym krokiem było dodanie dwóch wygenerowanych kluczy do secretów repozytorium na github:
![Screenshot 2023-12-03 at 17 55 17](https://github.com/aidian3k/todo-app/assets/93425971/5b4c1fdd-c7af-4da8-9483-e4a985b383c6)

## Napisanie odpowiedniej akcji github
Po dodaniu wrażliwych kluczy do secretów repozytorium mogłem już napisać działający piepline, który umożliwiał tworzenie błędu na 
platformie AzureDevops. Napisany plik main.yml prezentuje się następująco:
```
name: Studies project CI/CD integrated with azdo

on:
  push:
    branches: [ "master" ]
  pull_request:
    branches: [ "master" ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - name: Checkout the repository
      uses: actions/checkout@v3

    - name: Set up JDK 17 for selenium tests
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'

    - name: Set up node for angular project
      uses: actions/setup-node@v4
      with:
        node-version: 20

    - name: Install frontend project dependencies
      run: npm ci

    - name: Build Angular app and run Selenium tests
      run: npm run test-selenium

    - name: Creating bug AZDO tasks with failed selenium tests
      uses: stefanstranger/azuredevops-bug-action@1.1
      if: failure()
      with:
        OrganizationName: "final-project-mwo"
        PAT: "PAT"
        ProjectName: "mwo-github-azdo-integration"
        AreaPath: "mwo-github-azdo-integration\\Automation"
        IterationPath: "mwo-github-azdo-integration"
        GithubToken: "GithubToken"
        WorkflowFileName: "main.yml"
      env:
        PAT: ${{secrets.PAT}}
        GithubToken: ${{secrets.GITHUBRESTAPISECRET}}
```

Rozbijając pipeline na pojedyczne kroki, mogę to wytłumaczyć następująco:
* W pierwszym kroku ustawiamy odpowiednią wersję Javy na 17, tak żeby build testów w Selenium był prawidłowy
* W następnym punkcie ustawiamy odpowiednią wersję node, która jest potrzebna do uruchomienia projektu w angularze
* W kolejnym kroku instalujemy paczki za pomocą npm ci, tak żeby uruchomić projekt frontentowy na porcie 4200
* W następnym kroku korzystam z napisanego przeze mnie skryptu npm, który uruchamia aplikację frontend na porcie 4200
oraz uruchamia testy w selenium Java - kod prezentuje się następująco(package.json):
```
"test": "cd selenium-tests; ./gradlew test",
"test-selenium": "npx start-test http://localhost:4200"
```
Do uruchomienia oraz testów użyłem biblioteki start-test, która znacznie przyspieszyła konfigurację środowiska oraz build projektu.

* W ostatnim punkcie korzystam z taska stefanstranger/azuredevops-bug-action@1.1, który w przypadku gdy któryś z elementów build był failed,
to zostanie wysłany odpowiedni komunikat na platformę azure-devops - w naszym przypadk będzie to zgłoszenie buga. W celu skonfigurowania tego taska
użyłem Personal Access Key od AzureDevops oraz Github access key, który konfigurowałem w kolejnym punkcie. Oprócz tego konfiguruje, do którego miejsca
mają przypinać się informacje o błędach - w tym przypadku konfiguruje nazwę organizacji z 1 punktu, projektu oraz iteration path.

## Film z opisanej funkcjonalności
https://github.com/aidian3k/todo-app/assets/93425971/49e68095-9c03-4322-8cf7-065813866442

