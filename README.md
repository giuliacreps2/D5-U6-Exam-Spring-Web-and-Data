```
🌍 Sistema di Gestione Viaggi Aziendali
Questo progetto nasce con l'obiettivo di semplificare la logistica dei dipendenti, permettendo la gestione dei viaggi, delle prenotazioni e dei profili utente in modo automatizzato e sicuro.

💡 Il Progetto
L'applicazione è un'API REST costruita con Spring Boot che funge da cuore pulsante per l'organizzazione aziendale. Gestisce tre domini principali:Dipendenti: Anagrafica e gestione identità visiva.Viaggi: Pianificazione delle destinazioni e monitoraggio dello stato (Programmato/Completato).Prenotazioni: La logica che unisce dipendenti e viaggi, garantendo l'assenza di conflitti temporali.

🛠️ Architettura Tecnica e Scelte Implementative1. Gestione dei Media (Cloudinary)Per non appesantire il database e garantire performance elevate, la gestione degli avatar è stata affidata a Cloudinary.Al momento della creazione, ogni dipendente riceve un avatar generato dinamicamente basato sulle proprie iniziali.Tramite un endpoint dedicato (PATCH), è possibile caricare un file immagine reale, che viene processato e ospitato esternamente, salvando nel nostro database solo l'URL sicuro.2. Logica di Business e IntegritàIl sistema non è un semplice archivio dati. È presente una logica di controllo stringente:Validazione: Grazie a Spring Boot Validation, ogni dato in ingresso (DTO) viene controllato per assicurarne il formato (es. email valide, campi obbligatori).Controllo Conflitti: Prima di confermare una prenotazione, il sistema interroga il database per assicurarsi che il dipendente non sia già impegnato in un altro viaggio nella stessa data.3. Gestione Centralizzata degli ErroriÈ stato implementato un Global Exception Handler (@RestControllerAdvice). Questa scelta permette di:Evitare l'invio di errori generici al client.Restituire messaggi chiari e codici HTTP precisi (404 per risorse mancanti, 400 per violazioni della logica).Pulire il codice dei Controller, delegando la gestione delle eccezioni a un componente dedicato.

🚀 Guida all'utilizzo
Requisiti:
Java 17+
PostgreSQL
Account Cloudinary (per l'upload delle immagini)


Endpoint Principali
Metodo                         Endpoint                     Descrizione
POST                         /employees                     Crea un dipendente (Username, Nome, Cognome, Email)
PATCH                       /employees/{id}/avatar          Carica una foto profilo (form-data: profile_picture)
GET                         /employees                      Lista paginata e ordinabile dei                                                                  dipendenti
POST                        /travels                       Pianifica un nuovo viaggio
POST                        /bookings                      Assegna un dipendente a un viaggio                                                               (con controllo data)
<img width="1508" height="754" alt="Screenshot 2026-04-17 162602" src="https://github.com/user-attachments/assets/6e81a57f-3390-45d8-ac36-9272ea6552e2" />
<img width="896" height="543" alt="Screenshot 2026-04-17 125708" src="https://github.com/user-attachments/assets/194a11c2-7a4b-4118-b640-f8699ba2a045" />
<img width="1322" height="324" alt="Tabella4-Gestionale-Prenotazioni" src="https://github.com/user-attachments/assets/ac54ddfc-d5c9-44cb-a5e1-7e5425074d88" />

putStatusTravel
deleteTravel

<img width="1799" height="427" alt="Screenshot 2026-04-17 165013" src="https://github.com/user-attachments/assets/0a3b3f77-b97a-49d6-9d71-463a268fc430" />
<img width="1626" height="827" alt="Screenshot 2026-04-17 163629" src="https://github.com/user-attachments/assets/94e7e144-e669-4b3e-adba-019ba880a157" />
<img width="1508" height="379" alt="Screenshot 2026-04-17 163452" src="https://github.com/user-attachments/assets/9a26b975-2a4e-4d56-bd29-2c59299ff03f" />
<img width="1630" height="636" alt="Screenshot 2026-04-17 162726" src="https://github.com/user-attachments/assets/34b2fbae-46ca-41ba-b422-cf7f5bfc0beb" />
<img width="1637" height="758" alt="Screenshot 2026-04-17 162632" src="https://github.com/user-attachments/assets/f0d14b79-ffe1-40a9-8883-fce9cc3db812" />
<img width="1508" height="754" alt="Screenshot 2026-04-17 162602" src="https://github.com/user-attachments/assets/dd8dfae8-2779-4d65-8ec4-1f366a331145" />
<img width="896" height="543" alt="Screenshot 2026-04-17 125708" src="https://github.com/user-attachments/assets/6014336c-de49-401f-80bc-dcb1d663006f" />
