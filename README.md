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
