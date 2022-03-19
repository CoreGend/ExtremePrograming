package formation.xp;


public class Affichage {

    public String[] empty = {"   ", "   ", "   "};
    public String[] zero = {"┏━┓", "┃ ┃"  , "┗━┛" };
    public String[] one = {"  ┃", "  ┃", "  ┃"};
    public String[] two = {"╺━┓","┏━┛"  ,"┗━╸"  };
    public String[] three = {"╺━┓", "╺━┫" , "╺━┛"  };
    public String[] four = {"┃ ┃", "┗━┫" , "  ┃" };
    public String[] five = {"┏━╸","┗━┓"  ,"╺━┛"  };
    public String[] six = {"┏━╸","┣━┓"  ,"┗━┛"  };
    public String[] eight = {"┏━┓", "┣━┫" , "┗━┛" };

    private String multiConcat(String str, int k){
        String output = "";
        for (int i = 0; i<k; i++){
            output = output + str;
        }
        return output;
    }


    public String tableToString(int[][] table){

        //Ajout de la base de l'affichage
        String output = "";
        output+=multiConcat("█", 70);
        output += "\n";
        output+=multiConcat("█", 70);
        output += "\n";

        //Ajout des cases
        for (int i = 0; i < 4; i++){
            //Pour chaque ligne du tableau
            output += "██";
            for (int k =0; k<3; k++){
                //Pour chaque ligne d'affichage
                for (int j = 0; j < 4; j++){
                    //Pour chaque colonne du tableau
                    output+="██  ";
                    switch(table[i][j]){

                        case 2:
                            output+=multiConcat(" ", 5);
                            output+=two[k];
                            output+=multiConcat(" ", 6);

                            break;

                        case 4:
                            output+=multiConcat(" ", 5);
                            output+=four[k];
                            output+=multiConcat(" ", 6);
                            break;

                        case 8:
                            output+=multiConcat(" ", 5);
                            output+=eight[k];
                            output+=multiConcat(" ", 6);
                            break;

                        case 16:
                            output+=multiConcat(" ", 4);
                            output+=one[k];
                            output+=six[k];
                            output+=multiConcat(" ", 4);
                            break;

                        case 32:
                            output+=multiConcat(" ", 4);
                            output+=three[k];
                            output+=two[k];
                            output+=multiConcat(" ", 4);
                            break;

                        case 64:
                            output+=multiConcat(" ", 4);
                            output+=six[k];
                            output+=four[k];
                            output+=multiConcat(" ", 4);
                            break;

                        case 128:
                            output+=multiConcat(" ", 2);
                            output+=one[k];
                            output+=two[k];
                            output+=eight[k];
                            output+=multiConcat(" ", 3);
                            break;

                        case 256:
                            output+=multiConcat(" ", 2);
                            output+=two[k];
                            output+=five[k];
                            output+=six[k];
                            output+=multiConcat(" ", 3);
                            break;

                        case 512:
                            output+=multiConcat(" ", 2);
                            output+=five[k];
                            output+=one[k];
                            output+=two[k];
                            output+=multiConcat(" ", 3);
                            break;

                        case 1024:
                            output+=" ";
                            output+=one[k];
                            output+=zero[k];
                            output+=two[k];
                            output+=four[k];
                            output+=" ";
                            break;

                        case 2048:
                            output+=" ";
                            output+=two[k];
                            output+=zero[k];
                            output+=four[k];
                            output+=eight[k];
                            output+=" ";
                            break;

                        default:
                            output+=multiConcat(" ", 14);


                    }
                    output+="██";
                    if (j==3){
                        output+="██";
                    }
                }


            }
            //On a fini une ligne du tableau
            output+=multiConcat("█", 70);
        }
        output+=multiConcat("█", 70);

        return output;
    }






}
