package at.c02.bpj.client.service.converter;

public interface Converter<DtoType, ModelType> {
	DtoType convertToDto(ModelType model);

	ModelType convertToModel(DtoType dto);
}
