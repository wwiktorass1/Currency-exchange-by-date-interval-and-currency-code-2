# Valiutų kursai

Lietuvos Bankas suteikia galimybę gauti oficialius valiutų kursus. Daugiau informacijos galima
rasti lb.lt puslapyje:
https://www.lb.lt/lt/kasdien-skelbiami-euro-ir-uzsienio-valiutu-santykiai-skelbia-europos-centrinisbankas.

● Išanalizavau informaciją apie valiutų kursus, kuria galima gauti iš LBank.lt ir išanalizavau kaip veikia
užklausos parametrai.
● Sukūrtiau programą, kuri galėtų parsiųsti valiutų kursus pagal:
○ nurodytas datas arba periodą (nuo, iki).
○ nurodytų valiutų kodus.
● Programa turi pateikti nurodytų valiutų kursus ir suskaičiuoti valiutos kurso pokytį nuo
periodo pradžios iki periodo pabaigos.
● Duomenų saugojimas nebūtinas

Panaudojau LB Užsienio valiutų santykiai nurodytai dienai - getFxRates užklausą duomenims, kurie pateikiami tik Europos Centrinio Banko (EU) (duomenys bazėje nuo 2014-09-30).

## Programos paleidimas

Programą galima paleisti per komandinę eilute, arba IDE aplinkoje.

Kompiuteryje būtina turėti  JDK, JRE.


### Išvada:
Programos užklausų veikimas datos ilgam kelių metų intervalui greitesnis, nes mažiau reikia programai analizuoti XML dokmento duomenų.
Programos kodas su reikiamomis bibliotekomis: https://ufile.io/g1f2q6q6.
