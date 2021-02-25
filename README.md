# Team151
## C3
### Intento
C3 (Centro Commerciale in Centro) si pone l'obiettivo di favorire il commercio nei negozi fisici e rinsavire l'afflusso di persone che visitano il centro storico di piccoli comuni fornendo un servizio di prenotazione e di trasporto delle merci. La piattaforma offre modo ai commercianti di esporre i loro prodotti e permette ai clienti di effettuare la prenotazione della merce di loro interesse. Una volta pagata in negozio, la merce sarà spedita nel punto di consegna più vicino al cliente in appositi locker, dove sarà conservata fino al ritiro da parte dell'acquirente stesso. In questo modo, il cliente può agilmente continuare i propri acquisti senza doversi preoccupare di caricare la merce in macchina o di portarla con se.

Oggetto d'esame per il corso di Ingegneria del Software @ Unicam, a.a. 2020/2021.

### Funzionalità

Un utente che si iscrive a C3 deve scegliere un ruolo tra Cliente, Commerciante e Corriere.

Il <b>Cliente</b> avrà la possibilità di visualizzare i prodotti in vendita (filtrati per punto vendita o in base a determinate categorie) e di aggiungerli/rimuoverli al proprio shopping cart, proprio come accade in un tradizionale e-commerce. Una volta soddisfatto, il cliente può proseguire effettuando una prenotazione. Ciò comporta la presa in carico della stessa da parte di ogni commerciante responsabile dei punti vendita coinvolti nella prenotazione. Il cliente può ora dirigersi verso il punto vendita (o i punti vendita) dove ha piazzato una prenotazione e pagare la merce, identificandola tramite il codice generato nella ricevuta al momento della conferma. A questo punto la merce viene consegnata nel punto consegna da lui selezionato durante la procedura di prenotazione. Il cliente si dirigerà poi presso il punto consegna dove, inserendo un nuovo codice generato al momento del pagamento in negozio, avrà accesso all'armadietto a lui riservato contenente la merce ordinata.

Il ruolo di <b>Commerciante</b> si addice a chi è il possessore di uno o più punti vendita fisici già esistenti nel centro storico di interesse. Esso può registrare i punti vendita di cui è responsabile all’interno della piattaforma e caricare nella vetrina i propri prodotti. Al momento della prenotazione da parte di un cliente, viene generato un pacco per ogni punto vendita coinvolto. In tal modo, un commerciante gestisce solo il singolo pacco legato al punto vendita (o ai punti vendita) da lui gestito/i. Quando un cliente si reca in negozio per pagare un pacco, il commerciante, tramite la piattaforma, segnala l’effettivo acquisto della merce che era stata prenotata.

Il <b>Corriere</b> è colui che, al confermato pagamento di una prenotazione (quindi di tutti i pacchi che la compongono), viene selezionato dalla piattaforma per portare a termine l’incarico di consegna. Un corriere può visualizzare all’interno della propria area le prenotazioni assegnategli. Un corriere segnala prima il prelievo del pacco da uno specifico punto vendita e, solo quando tutti i pacchi di una prenotazione sono stati prelevati dai rispettivi punti vendita, si dirige al punto consegna dove, allo stesso modo, segnala la consegna della prenotazione completa.

### Documentazione

<h4>1^ ITERAZIONE</h4>

Per questa prima iterazione abbiamo stabilito una deadline di quattro settimane e abbiamo completato la fase di avvio identificando un gran numero di attori e una parte dei casi d’uso.
Ci siamo concentrati sui casi d’uso ritenuti sia più “rischiosi”, che più frequentemente utilizzati, nel nostro sistema applicando l’approccio “risk-driven” del processo unificato.
Abbiamo dedicato una settimana intera per la specifica dei tre casi d’uso scelti da sviluppare in questa iterazione (Elabora Prenotazione, Consegna Merce, Ritira Merce); abbiamo riscontrato difficoltà nella produzione del formato dettagliato dei casi d’uso, in quanto non sapevamo fino a che punto dettagliare il flusso degli eventi dei casi d’uso in questione.
Successivamente, abbiamo individuato le classi di analisi utilizzando le tecniche di identificazione apprese a lezione e dal libro di testo.
In seguito, nelle ultime due settimane siamo passati alla realizzazione del modello di progetto, che comprende Sequence Diagrams e classi di progetto. Abbiamo iniziato il modello di progetto con lo sviluppo di SSD a causa della difficoltà trovata nello sviluppo degli SD; una volta sviluppati, è stato più facile realizzare gli SD dei casi d’uso scelti per questa iterazione.
Negli ultimi giorni rimasti, ci siamo dedicati a sviluppare le classi di progetto, partendo dal modello di analisi e aggiungendo alle classi i metodi trovati durante la realizzazione degli SD.
A questo punto, abbiamo deciso di non implementare il codice per rispettare la deadline imposta all'inizio dell’iterazione.


<h4>2^ ITERAZIONE</h4>

Per la seconda iterazione abbiamo stabilito una deadline di tre settimane e abbiamo iniziato la fase di "Elaborazione", concentrandoci sul miglioramento di quanto fatto nell’iterazione precedente, sullo sviluppo di nuovi casi d’uso e sull’implementazione del codice.
Abbiamo acquisito maggiore competenza nell'utilizzo del software “Visual Paradigm”, permettendoci di lavorare ad una velocità superiore.
Nei primi giorni abbiamo proseguito lo sviluppo di attori e casi d’uso, riscontrando e correggendo, per quanto possibile, errori commessi durante la scorsa iterazione, dovuti alla nostra inesperienza nel procedere in questo processo di sviluppo.
Nella seconda settimana ci siamo dedicati all’aggiornamento delle classi di analisi individuate nella prima iterazione, applicando, anche in questa iterazione, le tecniche di identificazione delle classi; abbiamo riscontrato molti errori dovuti al fatto di non aver implementato codice nell'iterazione precedente.
Nella seconda parte della seconda settimana e successivamente nella terza, abbiamo sviluppato gli SD e implementato codice.
Durante l’implementazione del codice abbiamo riscontrato alcune incongruenze tra le classi di progetto e gli SD; a causa di ciò abbiamo convenuto che fosse controproducente implementare le parti di codice incongruenti e di conseguenza abbiamo implementato solamente ciò che ritenevamo corretto.
Nella prossima iterazione correggeremo tutti questi errori ed avremo sicuramente del codice funzionante.


<h4>3^ ITERAZIONE</h4>

Per la terza iterazione abbiamo stabilito una deadline di tre settimane. I casi d’uso che abbiamo scelto di sviluppare sono “Elabora prenotazione”, “Conferma prenotazione”, “Registrazione” e “Login”. Durante la settimana iniziale abbiamo all’incirca ripetuto i passi della seconda iterazione, cercando di capire se sarebbero emersi nuovi attori o casi d’uso e discutendo eventuali errori commessi nelle iterazioni precedenti. Durante la seconda settimana abbiamo portato a termine con successo tutta la fase di modellazione riguardante i sequence diagram dei casi d’uso da sviluppare, il modello d’analisi ed il modello di progetto. Nell'ultima settimana della terza iterazione, ci siamo interamente dedicati al codice. Partendo dai modelli realizzati siamo riusciti  a realizzare una codebase sufficiente, utilizzando la tecnologia delle REST APIs, inizialmente con difficoltà date dall'inesperienza nell'utilizzo di questa tecnologia.

<h4>4^ ITERAZIONE</h4>

Durante le quattro settimane della quarta iterazione abbiamo terminato la fase di "Elaborazione" e dato inizio a quella di “Costruzione”, che comporta meno lavoro sui modelli e più lavoro sulla codebase e la sua solidità (test e debugging). Abbiamo comunque sviluppato diversi casi d’uso gestionali per quanto riguarda l’aggiunta, la rimozione e la modifica di entità nel database. Oltre a questi abbiamo sviluppato i casi d’uso “Ricerca Articoli” e “Consegna Articoli”, in quanto fondamentali. La fase di modellazione dei casi d’uso è avvenuta più velocemente, sia a causa della ripetitività delle operazioni svolte nei casi d’uso implementati, sia dall’esperienza acquisita nelle iterazioni precedenti. Nella prime due settimane abbiamo quindi ottenuto un risultato soddisfacente in quanto a modelli d’analisi e di progetto. Nelle due restanti settimane abbiamo organizzato il lavoro diversamente rispetto alle iterazioni precedenti e condotto uno studio approfondito delle tecnologie necessarie. In questo modo, siamo riusciti ad ottenere una codebase solida e completa dal punto di vista delle funzionalità sviluppate in fase di modellazione, terminando così anche la fase di "Costruzione". Negli ultimi giorni di questa iterazione ci siamo infine dedicati a del refactoring della codebase per migliorare alcune imperfezioni.


### Workflow

Iterazione | #1 | #2 | #3 | #4 
--- | --- | --- | --- |--- 
Data Inizio | 9/11/20 | 7/12/20 | 4/01/21 | 25/01/21
Data Fine | 5/12/20 | 27/12/20 | 24/01/21 | 21/02/21 