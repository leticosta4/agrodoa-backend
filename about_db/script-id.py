#adicionar resto das relacoes que faltam nesse dict
#(e tirar o de estado-cidade)

sla = [
    {
        "tabela_base": "tipo",
        "tabela_com_chave": "usuario",
        "chave": "tipo_idtipo",
        "chave_referenciada": "idtipo",
        "fk": "fk_usuario_tipo1"
    },
    {
        "tabela_base": "tipo",
        "tabela_com_chave": "requisicao",
        "chave": "tipo_anterior",
        "chave_referenciada": "idtipo",
        "fk": "fk_requisicao_tipo1"
    },
    
]

for item in sla:
    prefixo_tabela_base = item['tabela_base'][:3].upper()
    if prefixo_tabela_base == 'USU' or prefixo_tabela_base == 'ADM':
        prefixo_tabela_base = 'CON'

    #apagar FK
    print(f"ALTER TABLE {item['tabela_com_chave']} DROP FOREIGN KEY {item['fk']};\n") #apagar chave 
    
    #alterar tipo coluna
    print(f"ALTER TABLE {item['tabela_base']} MODIFY COLUMN {item['chave_referenciada']} VARCHAR(7);")
    print(f"ALTER TABLE {item['tabela_com_chave']} MODIFY COLUMN {item['chave']} VARCHAR(7);\n")
    
    #alterar conteudo ja inserido
    print(f"UPDATE {item['tabela_base']} SET {item['chave_referenciada']} = CONCAT('{prefixo_tabela_base}', LPAD({item['chave_referenciada']}, 4, '0'));")
    print(f"UPDATE {item['tabela_com_chave']} SET {item['chave']} = CONCAT('{prefixo_tabela_base}', LPAD({item['chave']}, 4, '0'));\n")
    
    #recriar FK
    print(f"ALTER TABLE {item['tabela_com_chave']} ADD CONSTRAINT {item['fk']} FOREIGN KEY ({item['chave']}) REFERENCES {item['tabela_base']}({item['chave_referenciada']});\n")
    print("========================================================================================================================================\n\n")