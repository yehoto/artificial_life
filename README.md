# 🧬 Модель Естественного Отбора
![image](https://github.com/nuetka/artificial_life/assets/126546509/cfc29b0d-2271-4be2-9700-9a781851471b)

Inspired by Coding Challenge #69: Evolutionary Steering Behaviors of Daniel Shiffman [("The Coding Train")](https://www.youtube.com/watch?v=flxOkx0yLrY)

## 📝 Описание Модели
Модель естественного отбора представляет собой симуляцию экосистемы, где организмы взаимодействуют с окружающей средой, потребляя полезную еду и избегая яда. В процессе моделирования происходит естественный отбор, где выживают наиболее приспособленные особи.

## 📚 Основные Термины
- **мир** - Замкнутое поле, на котором происходят все процессы. Включает организмы, полезную еду и яд.  
- **Организм** - визуально представлен как белый равнобедренный треугольник на поле. Кончик треугольника - направление движение организма. 
- **Область видимости** - красные или зелёные окружности разных радиусов вокруг организмов. Восприятие еды/яда происходит только внутри контура.
- **Уровень притяжения к еде/яду** - красные или зелёные полосы, исходящие от центра организма в сторону его движения, если организм притягивает соответствующая пища, и в обратную - если отталкивает. Размер полос также имеет значение. Чем больше размер - тем сильнее сила притяжения/отталкивания.
- **Полезная еда** - слегка двигающиеся точки на поле зелёного цвета. При потреблении организм получает определённую прибавку к здоровью.
- **Яд** - слегка двигающиеся точки на поле красного цвета. При потреблении организм наносит определённый урон своему здоровью.
- **Клонирование** - производство организмом себе подобного
- **ДНК** - массив из 4-х элементов, содержащий информацию об уровне притяжения данного организма к еде/яду и его области видимости.
- **Мутация гена** - изменение родительского гена на определённую величину.

## 🔄 Жизненный Цикл Организмов
1. **Рождение**: Организм появляется на поле с уникальными характеристиками, заданными его ДНК.
2. **Питание**: Организм потребляет полезную еду и/или яд, что влияет на его здоровье.
3. **Размножение**: Организмы размножаются, производя потомство с возможными мутациями.
4. **Смерть**: Организм погибает либо от старости, либо от ухудшения здоровья (длительное голодание или потребление яда).

## 🌱 Динамика Экосистемы
- **Еда и Яд**: Постоянно воспроизводятся и потребляются организмами.
- **Естественный Отбор**: Организмы с более приспособленными характеристиками выживают и размножаются, передавая свои гены потомству.
- **Генетическое Разнообразие**: Мутации способствуют появлению новых комбинаций признаков, что может повысить шансы на выживание в изменяющихся условиях.
