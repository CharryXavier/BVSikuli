modes = ("Sum", "Res", "Mul", "Div")
option = select("Que operacion le gustaria hacer", options = modes)

click("1559049876543.png")

type("Calculadora"+Key.ENTER)

print("Se encontro Calculadora")

exists("1559050103001.png")

click(Pattern("1559050103001.png").targetOffset(-70,45))

if option == modes[0]:
    click(Pattern("1559050103001.png").targetOffset(169,120))
elif option == modes[1]:
    click(Pattern("1559050103001.png").targetOffset(165,45))
    
elif option == modes[2]:

    click(Pattern("1559050103001.png").targetOffset(173,-12))
else:
    
    click(Pattern("1559050103001.png").targetOffset(165,-91))
click(Pattern("1559050103001.png").targetOffset(-62,53))
click(Pattern("1559050103001.png").targetOffset(165,186))
