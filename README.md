# Proiect_JavaFX
# Administrare Formații Muzicale – JavaFX & MySQL

## Descriere
  Această aplicație desktop este dezvoltată folosind JavaFX și o bază de date MySQL. Aplicația permite gestionarea informațiilor despre formații muzicale, membri și contracte, oferind utilizatorului posibilitatea de a efectua operații CRUD (Create, Read, Update, Delete).
  Aplicația utilizează arhitectura MVC (Model-View-Controller), separând interfața grafică de logica aplicației și de interacțiunea cu baza de date.

---

## Tehnologii utilizate
- Java
- JavaFX
- MySQL
- JDBC
- FXML
- CSS

---

## Funcționalități

### Vizualizare
- afișarea datelor din baza de date în TableView

### Adăugare
- introducerea de noi înregistrări în baza de date

### Modificare
- actualizarea informațiilor existente

### Ștergere
- eliminarea înregistrărilor din baza de date

---

## Structura proiectului
Proiectul este organizat pe pachete:
  - **app.controller** -  conține clasele controller pentru operațiile CRUD
  - **app.db** - conține clasa DBOperations, responsabilă de conexiunea la baza de date
  - **app.model** -conține clasele model: Formatie, Membru, Contract
  - **fxml** -  conține fișierele care definesc interfața grafică.

---

## Baza de date

Aplicația utilizează trei tabele principale:

- **formatii**
- **membri**
- **contract**

Relația dintre formații și membri este de tip **many-to-many (M:N)**, realizată prin tabela **contract**.

---
## Interfața grafică

Interfața aplicației este realizată folosind **JavaFX** și **FXML**, iar stilizarea elementelor grafice este realizată prin **CSS**.

Aplicația include mai multe ferestre dedicate pentru gestionarea datelor din baza de date:

- vizualizarea datelor existente  
- adăugarea de noi înregistrări  
- modificarea informațiilor existente  
- ștergerea înregistrărilor  

Navigarea între aceste ferestre este realizată prin intermediul **controllerului principal al aplicației**, care gestionează deschiderea și schimbarea paginilor din interfață.

---

## Cum se rulează proiectul

1. Instalează Java JDK
2. Instalează MySQL
3. Creează baza de date și tabelele necesare
4. Configurează conexiunea la baza de date în proiect
5. Rulează aplicația din Eclipse sau IntelliJ

---

## Autor

Alexia Ioana Nedelcu  
Universitatea POLITEHNICA București  
Facultatea de Electronică, Telecomunicații și Tehnologia Informației
