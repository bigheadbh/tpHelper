package tpClass;

import utils.MyIO;

public class StarWarsCharacter implements Cloneable, Comparable<StarWarsCharacter> {

    private String name;
    private String birthYear;
    private String eyeColor;
    private String gender;
    private String hairColor;
    private String skinColor;
    private String homeWorld;

    private int height;

    private double mass;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }


    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getSkinColor() {
        return skinColor;
    }


    public void setHomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public void readCharacter(String s) {
        String replaced = s.replace("'", "");
        String [] json = replaced.split(",");

        for(int i = 0; i < json.length; i++) {
            String attribute = json[i];
            int index = attribute.indexOf(':');

            String substringWithValue = attribute.substring(index + 2, attribute.length());
            if(attribute.contains("name:")) {
                setName(substringWithValue);
            } else if (attribute.contains("height:")) {
                if(substringWithValue.equals("unknown")) {
                    setHeight(0);
                } else {
                    setHeight(Integer.parseInt(substringWithValue));
                }
            } else if(attribute.contains("mass:")) {
                String massAux = substringWithValue;
                if(massAux.equals("unknown")) {
                    setMass(0);
                } else {
                    for(int j = i+1; !json[j].contains("hair_color"); j++) {
                        massAux += ","+json[j];
                    }
                    massAux = massAux.replace(",", "");
                    setMass(Double.parseDouble(massAux));
                }
            } else if(attribute.contains("hair_color:")) {
                String hairColorAux = substringWithValue;
                for(int j = i+1; !json[j].contains("skin_color"); j++) {
                    hairColorAux += ","+json[j];
                }
                setHairColor(hairColorAux);
            } else if(attribute.contains("skin_color:")) {
                String skinColorAux = substringWithValue;
                for(int j = i+1; !json[j].contains("eye_color"); j++) {
                    skinColorAux += ","+json[j];
                }
                setSkinColor(skinColorAux);
            } else if(attribute.contains("birth_year:")) {
                setBirthYear(substringWithValue);
            } else if(attribute.contains("gender:")) {
                setGender(substringWithValue);
            } else if(attribute.contains("homeworld:")) {
                setHomeWorld(substringWithValue);
            } else if(attribute.contains("eye_color:")) {
                String eyeColorAux = substringWithValue;
                for(int j = i+1; !json[j].contains("birth_year"); j++) {
                    eyeColorAux += ","+json[j];
                }
                setEyeColor(eyeColorAux);
            }
        }
    }

    @Override
    public StarWarsCharacter clone() throws CloneNotSupportedException {
        return (StarWarsCharacter)super.clone();
    }

    public void printCharacter() {
        int mass = (int)getMass();
        if(getMass() % mass == 0 || mass == 0) {
            MyIO.println(" ## "+getName()+" ## "+getHeight()+" ## "+mass+" ## "+getHairColor()+" ## "
                    +getSkinColor()+" ## "+getEyeColor()+" ## "+getBirthYear()+" ## "+getGender()
                    +" ## "+ getHomeWorld()+" ## ");
        } else {
            MyIO.println(" ## "+getName()+" ## "+getHeight()+" ## "+getMass()+" ## "+getHairColor()+" ## "
                    +getSkinColor()+" ## "+getEyeColor()+" ## "+getBirthYear()+" ## "+getGender()
                    +" ## "+ getHomeWorld()+" ## ");
        }
    }

    @Override
    public int compareTo(StarWarsCharacter starWarsCharacter) {
        String value1 = this.name;
        String value2 = starWarsCharacter.getName();

        return value1.compareTo(value2);
    }
}
