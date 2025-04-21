function createFib(first, second) {
    let fst = first;
    let snd = second;
    return function() {
      let tmp = snd;
      snd = fst + snd;
      fst = tmp;
      return snd;
    }
  }
  counter1 = createFib(0,2);
  
  for (let i = 1; i <= 10; i++) {
    console.log(counter1());
  }
  console.log("\n");
  
  function createRandom() {
    const set = new Set();
   
    return function() {
    
      let num = Math.floor(Math.random() * 10);
      if (set.size >= 10) {
         return false;
      }
      while (set.has(num)) {
        num = Math.floor(Math.random() * 10);
      }
      set.add(num);
      return num;
    }
  }
  
  generator = createRandom();
  
  console.log(generator());
  for (let i = 1; i <= 10; i++) {
    console.log(generator());
  }