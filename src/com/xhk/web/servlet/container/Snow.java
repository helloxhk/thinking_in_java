package com.xhk.web.servlet.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xhk
 * @time 2018-11-30 10:32
 */
public class Snow {

	public static void main(String[] args) {
		/*List<Snow> snows1 = Arrays.asList(new Powder(), new Crusty(), new Slush());

		System.out.println(snows1);

		List<Snow> snows2 = Arrays.<Snow>asList(new Light(), new Heavy());

		System.out.println(snows2);

		List<Snow> snows3 = new ArrayList<>();
		Collections.addAll(snows3, new Light(), new Heavy());

		System.out.println(snows3);
*/
		Snow[] snows4 = {new Powder(), new Crusty(), new Slush()};
		System.out.println(snows4);
		System.out.println(Arrays.toString(snows4));

	}
}

class Powder extends Snow {

}

class Light extends Powder {

}

class Heavy extends Powder {

}

class Crusty extends Snow {

}

class Slush extends Snow {

}
