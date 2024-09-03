import React from "react";

function InputField({ id, label, value, onChange, required, type = "text"}) {
    return (
        <div>
            <label htmlFor={id}>{label}</label>
            {type === "textarea" ? (
                <textarea
                    id={id}
                    name={id}
                    value={value}
                    onChange={onChange}
                    required={required}
                />
            ) : (
                <input
                    type={type}
                    id={id}
                    name={id}
                    value={value}
                    onChange={onChange}
                    required={required}
                />
            )}
        </div>
    );
}

export default InputField;