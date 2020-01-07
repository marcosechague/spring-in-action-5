package com.mechague.tacocloud.data;

import com.mechague.tacocloud.domain.Taco;


public interface TacoRepository {

    Taco save(Taco design);
}
