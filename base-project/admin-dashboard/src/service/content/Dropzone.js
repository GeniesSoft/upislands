import axios from 'axios';
import React, {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import {API_BASE_URL} from "../../constants";

export default function Dropzone( { locationId } ) {
    const onDrop = useCallback(acceptedFiles => {

        const file = acceptedFiles[0];

        const formData = new FormData();
        formData.append('file',file);
        formData.append('contentText',"I love Mert Ege");

        axios.post(`${API_BASE_URL}/locations/${locationId}/content`,
            formData,
            {
                headers:{
                    "Content-Type":"multipart/form-data"
                }
            }).then(()=>{
            console.log("Upload is successfull");
        }).catch(err=>{
            console.log(err);
        })

    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

    return (
        <div {...getRootProps()}>
            <input {...getInputProps()} />
            {
                isDragActive ?
                    <p>Drop the files here ...</p> :
                    <p>Drag 'n' drop some files here, or click to select files</p>
            }
        </div>
    )
}