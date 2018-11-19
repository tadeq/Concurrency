# Teoria Współbieżności

# Zadanie domowe

### 2018

## 1 Zadanie domowe

Dane są:

- AlfabetA, w którym każda litera oznacza akcję,
- Zestaw transakcji na zmiennych
Słowo w oznaczające przykładowe wykonanie sekwencji akcji.

### 1.1 Zadanie (12 pkt.)

Napisz program, który:

1. Wyznacza relację zależnościD **(2 pkt.)**
2. Wyznacza relację niezależnościI **(2 pkt.)**
3. Wyznacza postać normalną Foaty FNF([w]) śladu[w] **(3 pkt.)**
4. Rysuje graf zależności w postaci minimalnej dla słowaw **(3 pkt.)**
5. Wyznacza postać normalną Foaty na podstawie grafu **(2 pkt.)**
Do zadania należy dostarzyć:
1. Opis programu z komentarzami
2. Wyniki działania dla przykładowych danych
3. Użycie języka programowania innego niż funkcyjny odejmuje po **0.
pkt.** od każdego podpunktu
1


## 2 Uwagi

- Można wykorzystać algorytmy (str. 10 i 43) z V. Diekert, Y. Metivier ́
    - Partial commutation and traces, [w:] Handbook of Formal Langu-
    ages, Springer, 1997, dostępne pod tutaj
- Do rysowania grafu można wykorzystać program Graphviz i format
    DOT. Przykład wizualizacji on-line
- W p. 4 można użyć sortowania topologicznego.

## 3 Format

Jedynym dopuszczalnym formatem dla części pisemnej jest plik pdf. Do-
zwolone jest użycie dowolnego jezyka funkcyjnego posiadającego kompi-
lator/interpreter oraz biblioteki dostępne w repozytoriach openSUSE Le-
ap 15.0 lub posiadającego darmowy interpreter online (razem ze wska-
zaniem linka do interpretera). Wymagane jest zachowania automatycznej
kompilacji (odpowiednik maven). Całość proszę dostarczyć w postaci ar-
chiwum zip. Przykładowe języki to np.: Scala, Erlang, Ocaml, Haskell.

## 4 Przykłady

### 4.1 Dane testowe 1

Dla danych:

- (a) x:=x+y
    (b) y:=y+ 2z
       (c) x:= 3x+z
    (d) z:=y _−_ z
- A= _{_ a, b, c, d _}_
- w=baadcb
Wyniki:
- D= _{_ (a, a),(a, b),(a, c),(b, a),(b, b),(b, d),(c, a),(c, c),(c, d),(d, b),(d, c),(d, d) _}_
- I= _{_ (a, d),(d, a),(b, c),(c, b) _}_


- FNF([w]) =(b)(ad)(a)(bc)
- Graf w formacie dot:
    digraph g{
       1 _−_ > 2
       2 _−_ > 3
       1 _−_ > 4
       3 _−_ > 5
       4 _−_ > 5
       3 _−_ > 6
       4 _−_ > 6
          1 [ l a b e l =b ]
          2 [ l a b e l =a ]
          3 [ l a b e l =a ]
          4 [ l a b e l =d ]
          5 [ l a b e l =b ]
          6 [ l a b e l =c ]
    }

### 4.2 Dane testowe 2

- (a) x:=x+ 1
    (b) y:=y+ 2z
       (c) x:= 3x+z
    (d) w:=w+v
       (e) z:=y _−_ z
          (f) v=x+v
- A ={a, b, c, d, e, f}
- w = acdcfbbe



