package com.mechague.tacocloud.representation.assembler;

import com.mechague.tacocloud.controller.DesignTacoController;
import com.mechague.tacocloud.domain.Taco;
import com.mechague.tacocloud.representation.TacoModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

    public TacoModelAssembler() {
        super(DesignTacoController.class, TacoModel.class);
    }

    @Override
    public TacoModel instantiateModel(Taco taco) {
        return new TacoModel(taco);
    }

    @Override
    public TacoModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }

}
