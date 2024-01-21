# IS727272 - Cordero Hernández Marco Ricardo - Tarea 6
class automata:
    def __init__(self):
        self.q0 = (True,"q2","q1")
        self.q1 = (True,"q3","q3")
        self.q2 = (True,"q5","q4")
        self.q3 = (True,"q6","q6")
        self.q4 = (True,"q8","q7")
        self.q5 = (True,"q8","q7")
        self.q6 = (False,"q6","q6")
        self.q7 = (True,"q8","q7")
        self.q8 = (False,"q8","q7")

        self.l = "ab"

    def __validar(self,w):
        for i in w:
            if(i not in self.l):
                return False
        else:
            return True

    def test(self, w):
        if(not self.__validar(w)):
            return "Cadena inválida"
        
        estado = self.q0
        estados = "q0"
        
        for i in range(len(w)):
            if(w[i] == "a"):
                estados += "/" + estado[1]
                estado = eval("self." + estado[1])
            elif(w[i] == "b"):
                estados += "/" + estado[2]
                estado = eval("self." + estado[2])
        
        return ("%s. Cadena %s" % (estados, "aceptada" if(estado[0]) else "rechazada"))

print(automata().test(input("Introduce tu cadena: ")))
