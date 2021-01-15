<h3>Часть 1.</h3>
<p>
  1. Создать класс Food с полями: name, expaireDate, createDate, price, disscount. На основе класса Food создать другие продукты.
</p>
<p>
  2. Создать классы хранилище продуктов Warehouse, Shop, Trash.
</p>
<p>
  3. Создать класс обработчик перераспределения продуктов в место использования. ControllQuality. Класс должен перераспределять еду по хранилищам в зависимости от условий:<br>
</p>
<p>
&nbsp;&nbsp;&nbsp;&nbsp; 3.1. Если срок годности израсходован меньше чем на 25% направить в Warehouse.<br>
&nbsp;&nbsp;&nbsp;&nbsp; 3.2 Если срок годности от 25% до 75% направить в Shop<br>
&nbsp;&nbsp;&nbsp;&nbsp; 3.3. Если срок годности больше 75% то выставить скидку на продукт и отправить в Shop<br>
&nbsp;&nbsp;&nbsp;&nbsp; 3.4. Если срок годности вышел. Отправить продукт в мусорку.<br>
</p>
<p>
В данной задаче надо использовать шаблон проектирования - https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm
</p>
<p>
Нельзя использовать instanceOf или if ("Shop".equals(container.getName())
</p>
