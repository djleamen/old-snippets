import re

def validate_variable(value):
    variable_re = re.compile(r'^[a-zA-Z_][a-zA-Z0-9_]*$')
    return bool(variable_re.match(value))

def validate_domain(value):
    domain_re = re.compile(r'^[a-z]([a-z0-9_]*[a-z0-9])?(\.[a-z]([a-z0-9_]*[a-z0-9])?)*\.(com|ca|org)$')
    return bool(domain_re.match(value))

def validate_phone(value):
    phone_re = re.compile(r'^(?:\(\d{3}\)|\d{3}-)?\d{3}-\d{4}$')
    return bool(phone_re.match(value))