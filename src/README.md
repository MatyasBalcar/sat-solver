# SAT Solver (DPLL + Unit Propagation)

## Sestavení
```aiignore
cd src/ &&
javac *.java
```

## Spuštění

```aiignore
java Solver input.cnf
```


## Výstup
- 1. řádek: SAT nebo UNSAT
- 2. řádek: seznam pravdivých literálů (pokud SAT)
- 3. řádek: čas načtení v sekundách
- 4. řádek: čas výpočtu v sekundách
- 5. řádek: počet unit propagací
- 6. řádek: počet rozhodovacích vrcholů


## Pouziti batchsolveru

#### Pripsal jsem tenhle pomocny program, ktery vypocita nekolik cnf seriove
### Pouziti
#### java BatchSolver [start] [end] [path] [optional: prefix]
Napriklad
- Pro vyreseni vsech 20 vars sat cnf
```aiignore
java BatchSolver 1 999 benchmarks/100vars/sat/

```
- nemusime pouzivat prefix protoze defaultni file bereme 1-999.cnf
- Pokud ale chceme pocitat treba vertices 
```aiignore
java BatchSolver 1 100 benchmarks/coloring/30vertices/sat/ flat30-
```

- Prefix pred kazdym souborem je flat30- tak ho pouzijeme 

### Vysledky
- Pocet vyresenych problemu
- Prumerny cas na problem
- Celkovy cas
- Prumerny pocet unit propagaci
- take samozrejme vypise jednotlive vypocty do konzole
