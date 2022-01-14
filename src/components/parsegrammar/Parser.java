package components.parsegrammar;

import components.ludemeblock.grammar.Constructor;
import components.ludemeblock.grammar.input.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private List<Grammar> getGrammar(){
        List<Grammar> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\fmele\\Documents\\University\\Year 2\\MaRBLe\\Ludii-Visual-Editor\\grammar-1.3.0.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(records.size() > 0){
                    Grammar previous_grammar = records.get(records.size()-1);
                    String current_name = values[0].substring(1,values[0].length()-1);
                    if(previous_grammar.NAME.equals(current_name)){
                        previous_grammar.addConstructor(values[1]);
                    }
                    else{
                        Grammar new_grammar = new Grammar(current_name);
                        new_grammar.addConstructor(values[1]);
                        records.add(new_grammar);
                    }
                }
                else {
                    Grammar new_grammar = new Grammar(values[0]);
                    new_grammar.addConstructor(values[1]);
                    records.add(new_grammar);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        records.remove(0); // remove header
        return records;
    }

    private final List<Character> GRAMMAR_CHARACTERS = List.of('[',']','<','>','{','}','|',':','(',')');

    public Parser(){
        for(Grammar g : getGrammar()){
            LudemeBlueprint ludemeBlueprint = new LudemeBlueprint(g.NAME);
            for(String c : g.constructors){
                // get all required inputs
                ArrayList<Input> inputs = new ArrayList<Input>();
                String inputs_string = c.substring(1,c.length()-1);
                String constructorName = null;
                // check whether begins with its name TODO: board.track --> (track X X )
                boolean starts_with_name = inputs_string.startsWith(g.NAME);
                // remove
                if(starts_with_name){
                    inputs_string = inputs_string.replaceFirst(g.NAME+" ", "");
                    if(!GRAMMAR_CHARACTERS.contains(inputs_string.charAt(0))){
                        constructorName = inputs_string.substring(0, inputs_string.indexOf(" "));
                        inputs_string = inputs_string.substring(0, inputs_string.indexOf(" "));
                    }
                }
                System.out.println("name: " + g.NAME);
                System.out.println("inputs string: " + inputs_string);
                while(inputs_string.length() > 0) {
                    char first_letter = inputs_string.charAt(0);
                    switch (first_letter) {
                        case '<':
                            // ludemeInput, no choice, not optional, no collection
                            int endIndex = inputs_string.indexOf('>');
                            // TODO: save input
                            String input = inputs_string.substring(0, inputs_string.indexOf('>') + 1);
                            inputs_string = inputs_string.substring(inputs_string.indexOf('>') + 1).trim();
                            System.out.println("is ludeme input: " + input);
                            break;
                        case '[':
                            // optional. can be a choice also
                            // TODO: should be recursive
                            // repeat until ] and then continue with substring
                            break;
                        case '{':
                            // collection

                            // repeat until } and then continue with substring
                            break;
                        case '(':
                            // usually contains many or statements ( -> choice )

                            // repeat until ) and then continue with substring
                            break;
                    }
                    System.out.println("\n");
                }
                // TODO: Do nested [ ] exist? e.g. [ a | b | [ c | d ] ] -> i dont think so?
                // e.g. : game <string> TODO: Input states whether it includes name (e.g. game, move) in the beginning
                // e.g. : <match>
                // e.g. : move Set Rotation [<moves.to>] [{<int>} | <int>] [previous:<boolean>] [next:<boolean>] [<then>]
                Constructor constructor = new Constructor(inputs, c);
                if(constructorName != null){
                    constructor.setName(constructorName);
                }
            }


        }
    }

}
