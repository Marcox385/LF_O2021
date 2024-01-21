#IS727272 - Cordero Hernández Marco Ricardo - Simulación de AFD
class FSM:
    dump = None
    
    def __init__(self,fileName):
        with(open(fileName)) as specs:
            self.dump = specs.read().split("\n")

        self.string = self.dump[0] # String to evaluate (string)
        self.symbols = self.dump[1].split(";") # Allowed input symbols (list)
        if(not self.validateString()): # If character in given string not in given symbols
            return print("Error: cadena no válida con alfabeto proporcionado")
        
        self.initialState = self.dump[2] # Initial state (string)
        self.finalStates = self.dump[3].split(";") # Set of final state(s) (list)
        self.states = self.getStates() # Set of names for available states (list)
        self.transitions = self.getTransitions() # Set of transitions (dictionary)
        
        self.evaluate() # Evaluation of string

    def validateString(self):
        for elem in self.string:
            if(elem not in self.symbols):
                return False
        else:
            return True

    def getStates(self): # Returns all the states in the FSM
        # Although its specified that the states will be in order, this ensures it
        states = [self.initialState]
        holder = None

        for row in self.dump[4:]:
            holder = row.split(";")

            for elem in holder:
                if(elem not in states and elem != ""):
                    states.append(elem)
        else:
            states.sort()
            return states

    def getTransitions(self): # Handles states and transitions as dictionary
        transitions = {}

        for state, transitionsRow in zip(self.states, self.dump[4:]):
            transitions[state] = {}
            
            for value, transition in zip(self.symbols, transitionsRow.split(";")):
                if(transition != ""):
                    transitions[state][value] = transition
        else:
            return transitions

    def evaluate(self): # Evaluates given string
        splitString = [i for i in self.string]
        visitedStates = []
        nextState = self.initialState

        for i in splitString:
            visitedStates.append(nextState)
            nextState = self.transitions[nextState][i]
        else:
            visitedStates.append(nextState)
            print("\n" + ("Aceptada" if(nextState in self.finalStates) else "Rechazada"))
            print("Secuencia de estados: ", end="")
            print(*visitedStates, sep="/", end="")
            print("/")
            
                        
def veriFile(fileName):
    mode = 0
    try:
        if(fileName[-1:-5:-1][::-1] == ".txt"):
            holder = open(fileName)
            mode = 1
        else:
            holder = open(fileName + ".txt")
            mode = 2

        holder.close()
        return mode
    except:
        return 0


while(1):
    fileName = input("Introduce el nombre de tu archivo (solo .txt): ")

    mode = veriFile(fileName)

    if(mode):
        try:
            FSM(fileName if(mode == 1) else fileName + ".txt")

            dec = input("\n¿Deseas probar con otro archivo? -> ")
            if(dec.upper() not in ("S","SI","SÍ","1","Y","YES")):
                break
        except:
            input("\nUn error inesperado ha ocurrido\n"
                  "Presiona ENTER para continuar...")
            break
    else:
        print("El nombre ingresado no es válido, intenta de nuevo...\n")
