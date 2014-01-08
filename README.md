#[Student MANAGER Project](https://github.com/PiglooPOO/StudentManagerProject)

## TODO

Verbes en ER
Dans les menus, return int (1 pour menu précédent, -1 pour nemu principal et -2 pour erreur)

- Ajouter un étudiant \[done\]

- Rechercher un étudiant (par no étudiant \[done\], nom \[done\], prenom \[done\], matière \[done\], filiaire \[done\], année \[done\])
> - l'afficher \[done\]
> - Inscrire un élève dans une filière et année
> - Modifier des informations
> - Attribuer des notes \[done\] mais ne vérifie pas si la matière est dans la formation suivie
> - Afficher ses moyennes \[done\] mais moyenne générale ne prend pas en compte coef pour l'instant, j'attend de finir les conditions d'ajouts de notes.
> - Editer attestation de réussite \[done\] MAIS PAS EN ODT

- Créer une filière \[done\]
- Rechercher une filière \[done\]
> - Ajouter une matière, des élèves
> - Afficher ses éleves par année (pouvoir y acceder) \[done\]
- Rechercher une matière \[done\]
> - Ajouter à une filiaire pour une année

- Ajouter des notes
> - Pour année en cours en recherchant formation puis en choisissant la matière

- Créer une année
> - Copier une formation de l'année précédente en faisant passer les élèves year_formation_student.nbyear +1 si < formation.nbyear

- Afficher les diplomés
> - Editer une attestation de réussite

- Charger une base de données?
- Enregistrer la base de données? \[done\]
- Modifier la configuration?


## HOW TO

### COMMENT
Example :
```
/**
* Description about the foo function
* @param <id> is student id to blabla
* @param <str> a fucking string to bla.
* @see <testMeToo>()
* @return <boolean> explainations
*/
public boolean foo(int id, String str)
```

### Import Project

- Open Eclipse
- Right click on project pannel
- "Import"
- General > Existing Projects into Workspace
- "Next"
- /!\ Select EclipseProject folder, not StudentManagerProject !
- "Finish"

##[/!\ SQL FILE IN /](https://github.com/PiglooPOO/StudentManagerProject/blob/master/smp.sql)

Création d'une base de donnée héberger : accès a PhpMyAdmin : [https://phpmyadmin.alwaysdata.com](https://phpmyadmin.alwaysdata.com)
id: pigloopoo
mdp: Minions77

### Work

- /!\ ALWAYS SYNC BEFORE WORKING /!\

### Commit

- Put your name in commit name, and a clear description.

- In case of error, use Git Shell, just by entering in repository and doing

$ git commit -m "message"

It will show you errors to fix.


## THE PROJECT

<talk and present>

